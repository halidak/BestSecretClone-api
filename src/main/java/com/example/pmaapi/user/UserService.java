package com.example.pmaapi.user;

import com.example.pmaapi.config.JwtService;
import com.example.pmaapi.product.Product;
import com.example.pmaapi.product.ProductRepository;
import com.example.pmaapi.user.request.ChangePassword;
import com.example.pmaapi.user.request.Favourites;
import com.example.pmaapi.user.request.UpdateUser;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public void addToFavourites(Favourites request){
        User user = userRepository.findById(request.getUserId()).orElse(null);
        Product product = productRepository.findById(request.getProductId()).orElse(null);

        user.getFavourites().add(product);

        userRepository.save(user);
    }

    public void removeFromFavourites(Favourites request){
        User user = userRepository.findById(request.getUserId()).orElse(null);
        Product product = productRepository.findById(request.getProductId()).orElse(null);

        user.getFavourites().remove(product);

        userRepository.save(user);
    }

    //update user
    public UserDTO updateUser(UpdateUser request, String token){
        User userToUpdate = jwtService.getUserFromToken(token);

        if(userToUpdate != null){
            userToUpdate.setFirstName(request.getFirstName());
            userToUpdate.setLastName(request.getLastName());
            userToUpdate.setEmail(request.getEmail());
            userToUpdate.setProfilePicture(request.getProfilePicture());
            userRepository.save(userToUpdate);

            UserDTOMapper userDTOMapper = new UserDTOMapper();
            UserDTO userDTO = userDTOMapper.apply(userToUpdate);

            return userDTO;
        } else {
            throw new RuntimeException("Korisnik nije pronađen.");
        }
    }

    //change password
    public UserDTO changePassword(ChangePassword request, String token){
        User userToUpdate = jwtService.getUserFromToken(token);

        if (userToUpdate != null) {
            String hashedOldPassword = passwordEncoder.encode(request.getOldPassword());

            if (passwordEncoder.matches(request.getOldPassword(), userToUpdate.getPassword())) {
                String hashedNewPassword = passwordEncoder.encode(request.getNewPassword());
                userToUpdate.setPassword(hashedNewPassword);
                userRepository.save(userToUpdate);

                UserDTOMapper userDTOMapper = new UserDTOMapper();
                UserDTO userDTO = userDTOMapper.apply(userToUpdate);

                return userDTO;
            } else {
                throw new RuntimeException("Stara lozinka nije ispravna.");
            }
        } else {
            throw new RuntimeException("Korisnik nije pronađen.");
        }
    }
}
