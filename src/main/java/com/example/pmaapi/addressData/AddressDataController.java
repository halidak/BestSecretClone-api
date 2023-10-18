package com.example.pmaapi.addressData;

import com.example.pmaapi.addressData.request.AddAddressRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressDataController {

    private final AddressDataService addressDataService;

    @PostMapping("/add")
    public ResponseEntity<AddressData> addAddress(@RequestBody AddAddressRequest request) {
        return ResponseEntity.ok(addressDataService.addAddress(request));
    }

}
