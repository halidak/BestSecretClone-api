package com.example.pmaapi.addressData;

import com.example.pmaapi.addressData.request.AddAddressRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressDataController {

    private final AddressDataService addressDataService;

    @PostMapping("/add")
    public ResponseEntity<AddressData> addAddress(@RequestBody AddAddressRequest request,
                                                  @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity.ok(addressDataService.addAddress(request, token));
    }

}
