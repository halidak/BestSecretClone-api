package com.example.pmaapi.user;

import com.example.pmaapi.user.request.ChangePassword;
import com.example.pmaapi.user.request.Favourites;
import com.example.pmaapi.user.request.UpdateUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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

    //update user
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UpdateUser request, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        UserDTO userDTO = userService.updateUser(request, token);
        return ResponseEntity.ok(userDTO);
    }

    //change password
    @PutMapping("/change-password")
    public ResponseEntity<UserDTO> changePassword(@RequestBody ChangePassword request, @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        UserDTO userDTO = userService.changePassword(request, token);
        return ResponseEntity.ok(userDTO);
    }
}
