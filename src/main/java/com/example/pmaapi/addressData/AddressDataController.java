package com.example.pmaapi.addressData;

import com.example.pmaapi.addressData.request.AddAddressRequest;
import com.example.pmaapi.addressData.request.UpdateAddress;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/addresses")
    public ResponseEntity<List<AddressData>> getAddressesFromUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        List<AddressData> addresses = addressDataService.getAddressesFromUser(token);
        return ResponseEntity.ok(addresses);

    }

    @GetMapping("/address/{id}")
    public ResponseEntity<AddressData> getAddress(@PathVariable Long id)
    {
        AddressData address = addressDataService.getAddressById(id);
        if (address != null) {
            return ResponseEntity.ok(address);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id) {
        addressDataService.deleteAddressById(id);
        return ResponseEntity.ok("Adresa sa ID " + id + " je uspešno obrisana.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AddressData> updateAddress(@PathVariable Long id, @RequestBody UpdateAddress request) {
        AddressData updatedAddress = addressDataService.updateAddress(id, request);

        if (updatedAddress != null) {
            return ResponseEntity.ok(updatedAddress);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
