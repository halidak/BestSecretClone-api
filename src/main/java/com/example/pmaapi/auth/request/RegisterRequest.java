package com.example.pmaapi.auth.request;

import com.example.pmaapi.user.response.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private Role role;


}
