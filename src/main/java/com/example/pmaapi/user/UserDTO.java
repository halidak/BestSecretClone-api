package com.example.pmaapi.user;

public record UserDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String profilePicture
) {
}
