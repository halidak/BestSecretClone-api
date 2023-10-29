package com.example.pmaapi.auth.request;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String email;
    private String code;
    private String password;
}
