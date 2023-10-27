package com.example.pmaapi.user;

import com.example.pmaapi.user.request.Favourites;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add-to-fav")
    public ResponseEntity<String> addToFav(@RequestBody Favourites request){
        userService.addToFavourites(request);
        return ResponseEntity.ok("Product has been added to favourites");
    }

    @DeleteMapping("/remove-from-fav")
    public ResponseEntity<String> remove(@RequestBody Favourites request){
        userService.removeFromFavourites(request);
        return ResponseEntity.ok("Product has been removed from favourites");
    }
}
