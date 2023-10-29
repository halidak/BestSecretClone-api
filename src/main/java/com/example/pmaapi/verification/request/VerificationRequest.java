package com.example.pmaapi.verification.request;

import lombok.Data;

@Data
public class VerificationRequest {
    private String email;
    private String code;
}
