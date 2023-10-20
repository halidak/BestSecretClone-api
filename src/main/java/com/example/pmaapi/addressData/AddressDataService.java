package com.example.pmaapi.addressData;

import com.example.pmaapi.addressData.request.AddAddressRequest;
import com.example.pmaapi.config.JwtService;
import com.example.pmaapi.user.User;
import com.example.pmaapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressDataService {

    private final AddressDataRepository addressDataRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AddressData addAddress(AddAddressRequest request, String token) {
        try {
            var user = jwtService.getUserFromToken(token);

            AddressData data = AddressData.builder()
                    .address(request.getAddress())
                    .ptt(request.getPtt())
                    .city(request.getCity())
                    .phoneNumber(request.getPhoneNumber())
                    .user(user)
                    .build();

            return addressDataRepository.save(data);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging.
            return null; // Return a proper response or throw a custom exception as needed.
        }
    }

}
