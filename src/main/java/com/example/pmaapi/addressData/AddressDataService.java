package com.example.pmaapi.addressData;

import com.example.pmaapi.addressData.request.AddAddressRequest;
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

    public AddressData addAddress(AddAddressRequest request){
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if(user == null) {
            throw new RuntimeException("User not found.");
        }
        AddressData data = AddressData.builder()
                .address(request.getAddress())
                .ptt(request.getPtt())
                .city(request.getCity())
                .phoneNumber(request.getPhoneNumber())
                .user(user)
                .build();

        return  addressDataRepository.save(data);
    }
}
