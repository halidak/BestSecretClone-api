package com.example.pmaapi.auth;

import com.example.pmaapi.auth.request.LoginRequest;
import com.example.pmaapi.auth.request.RegisterRequest;
import com.example.pmaapi.auth.request.ResetPasswordRequest;
import com.example.pmaapi.auth.response.AuthenticationResponse;
import com.example.pmaapi.config.JwtService;
import com.example.pmaapi.user.User;
import com.example.pmaapi.user.UserDTOMapper;
import com.example.pmaapi.user.UserRepository;
import com.example.pmaapi.verification.Verification;
import com.example.pmaapi.verification.VerificationRepository;
import com.example.pmaapi.verification.VerificationType;
import com.example.pmaapi.verification.request.VerificationRequest;
import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.SendEmailsRequest;
import com.mailjet.client.transactional.TrackOpens;
import com.mailjet.client.transactional.TransactionalEmail;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDTOMapper userDTOMapper;
    @Value("${mailjet_apikey}")
    private String mailjetApiKey;
    @Value("${mailjet_apikeySecret}")
    private String mailjetApiSecret;

    private final VerificationRepository verificationRepository;
    private EntityManager entityManager;


    public AuthenticationResponse register(RegisterRequest request) throws MailjetException {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new RuntimeException("Email address is already registered.");
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .verified(false)
                .build();

        userRepository.save(user);
        log.info("Mailjet API Key: {}", mailjetApiKey);
        log.info("Mailjet API Secret: {}", mailjetApiSecret);


        sendVerificationEmail(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(userDTOMapper.apply(user))
                .build();
    }
    public AuthenticationResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            var user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .user(userDTOMapper.apply(user))
                    .build();
        } catch (Exception e) {
            throw new IllegalStateException("Invalid email or password");
        }
    }

    //send verification email
    @Transactional
    private void sendVerificationEmail(User user) throws MailjetException {
        var rand = new SecureRandom();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++)
            code.append(rand.nextInt(10));
        var verification = Verification.builder()
                .user(user)
                .code(code.toString())
                .type(VerificationType.EMAIL_VERIFICATION)
                .build();
        verificationRepository.save(verification);

        ClientOptions options = ClientOptions.builder()
                .apiKey(mailjetApiKey)
                .apiSecretKey(mailjetApiSecret)
                .build();

        MailjetClient client = new MailjetClient(options);

        TransactionalEmail email = TransactionalEmail
                .builder()
                .to(new SendContact(user.getEmail(), String.format("%s %s", user.getFirstName(), user.getLastName())))
                .from(new SendContact("halida.karisik6@gmail.com", "BestSecret Team"))
                .htmlPart("<h1>Please enter the code in your app</h1><p>Your code: " + code.toString() + "</p>")
                .subject("Email verification")
                .trackOpens(TrackOpens.ENABLED)
                .build();

        SendEmailsRequest emailRequest = SendEmailsRequest.builder().message(email).build();
        emailRequest.sendWith(client);
    }

    public AuthenticationResponse verifyEmail(VerificationRequest request) {
        var verificationOptional = verificationRepository
                .findByCodeAndUserEmail(request.getCode(), request.getEmail());
        if(verificationOptional.isEmpty())
            throw new IllegalStateException("Invalid code");
        var verification = verificationOptional.get();
        if(verification.getType() != VerificationType.EMAIL_VERIFICATION)
            throw new IllegalStateException("Invalid code");
        var user = verification.getUser();
        var now = LocalDateTime.now();
        var oneDayAgo = now.minusDays(1);
        long minutesDifference = ChronoUnit.MINUTES.between(verification.getCreatedAt(), LocalDateTime.now());

        /*if(minutesDifference > 15)
            throw new IllegalStateException("Code expired");

        entityManager.createQuery("DELETE FROM Verification t WHERE t.createdAt < :dayAgo")
                .setParameter("dayAgo", oneDayAgo)
                .executeUpdate();*/

        user.setVerified(true);
        userRepository.save(user);
        verificationRepository.delete(verification);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(userDTOMapper.apply(user))
                .build();
    }

    @Transactional
    private void sendPasswordResetEmail(User user) throws MailjetException {
        var rand = new SecureRandom();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++)
            code.append(rand.nextInt(10));
        var verification = Verification.builder()
                .user(user)
                .code(code.toString())
                .type(VerificationType.PASSWORD_RESET)
                .build();
        verificationRepository.save(verification);

        ClientOptions options = ClientOptions.builder()
                .apiKey(mailjetApiKey)
                .apiSecretKey(mailjetApiSecret)
                .build();

        MailjetClient client = new MailjetClient(options);

        TransactionalEmail email = TransactionalEmail
                .builder()
                .to(new SendContact(user.getEmail(), String.format("%s %s", user.getFirstName(), user.getLastName())))
                .from(new SendContact("halida.karisik6@gmail.com", "BestSecret Team"))
                .htmlPart("<h1>Please enter the code in your app</h1><p>Your code: " + code.toString() + "</p>")
                .subject("Email verification")
                .trackOpens(TrackOpens.ENABLED)
                .build();

        SendEmailsRequest emailRequest = SendEmailsRequest.builder().message(email).build();
        emailRequest.sendWith(client);
    }

    public void forgotPassword(String email) throws MailjetException {
        var user = userRepository.findByEmail(email).orElseThrow(
                () -> new IllegalStateException("User with this email does not exist")
        );
        sendPasswordResetEmail(user);

    }

    public void resetPassword(ResetPasswordRequest request) {
        var verificationOptional = verificationRepository.findByCodeAndUserEmail(request.getCode(), request.getEmail());
        if(verificationOptional.isEmpty())
            throw new IllegalStateException("Invalid code");
        var verification = verificationOptional.get();
        if(verification.getType() != VerificationType.PASSWORD_RESET)
            throw new IllegalStateException("Invalid code");
        var user = verification.getUser();
        var now = LocalDateTime.now();
        var oneDayAgo = now.minusDays(1);
        long minutesDifference = ChronoUnit.MINUTES.between(verification.getCreatedAt(), LocalDateTime.now());
       /* if(minutesDifference > 15)
            throw new IllegalStateException("Code expired");

        entityManager.createQuery("DELETE FROM Verification t WHERE t.createdAt < :dayAgo")
                .setParameter("dayAgo", oneDayAgo)
                .executeUpdate();*/

        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        verificationRepository.delete(verification);

    }

}
