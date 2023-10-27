package com.example.pmaapi.user;

import com.example.pmaapi.product.Product;
import com.example.pmaapi.product.ProductRepository;
import com.example.pmaapi.user.request.Favourites;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

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
}
