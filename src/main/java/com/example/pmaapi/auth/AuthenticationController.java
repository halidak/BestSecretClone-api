package com.example.pmaapi.auth;

import com.example.pmaapi.auth.request.ForgotPasswordRequest;
import com.example.pmaapi.auth.request.LoginRequest;
import com.example.pmaapi.auth.request.RegisterRequest;
import com.example.pmaapi.auth.request.ResetPasswordRequest;
import com.example.pmaapi.auth.response.AuthenticationResponse;
import com.example.pmaapi.verification.request.VerificationRequest;
import com.mailjet.client.errors.MailjetException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody RegisterRequest request
    )throws MailjetException {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody LoginRequest request
    ){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/verify-email")
    public ResponseEntity<AuthenticationResponse> verifyEmail(@RequestBody VerificationRequest request) {
        AuthenticationResponse response = authService.verifyEmail(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity forgotPassword(@RequestBody ForgotPasswordRequest request) throws MailjetException {
        authService.forgotPassword(request.getEmail());
        return ResponseEntity.ok("Email sent");
    }

    @PostMapping("/reset-password")
    public ResponseEntity resetPassword(@RequestBody ResetPasswordRequest request) throws MailjetException {
        authService.resetPassword(request);
        return ResponseEntity.ok("Password reset");
    }
}
