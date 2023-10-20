package com.example.pmaapi.addressData.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddAddressRequest {
    private String phoneNumber;
    private String address;
    private String city;
    private String ptt;
    private Long userId;
}
