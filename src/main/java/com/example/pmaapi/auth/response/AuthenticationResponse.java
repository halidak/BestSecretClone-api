package com.example.pmaapi.auth.response;

import com.example.pmaapi.user.response.User;
import com.example.pmaapi.user.response.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private UserDTO user;
    private String token;
}
