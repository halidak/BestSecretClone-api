package com.example.pmaapi.addressData;

import com.example.pmaapi.addressData.request.AddAddressRequest;
import com.example.pmaapi.addressData.request.UpdateAddress;
import com.example.pmaapi.config.JwtService;
import com.example.pmaapi.user.User;
import com.example.pmaapi.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<AddressData> getAddressesFromUser(String token){
        var user = jwtService.getUserFromToken(token);
        return  addressDataRepository.findAddressesByUserId(user.getId());
    }

    public AddressData getAddressById(Long id){
        return addressDataRepository.findById(id).orElse(null);
    }

    public void deleteAddressById(Long id){
        addressDataRepository.deleteById(id);
    }

    public AddressData updateAddress(Long id, UpdateAddress request) {
        AddressData existingAddress = addressDataRepository.findById(id).orElse(null);

        if (existingAddress != null) {
            // Ako adresa postoji, ažurirajte je
            existingAddress.setAddress(request.getAddress());
            existingAddress.setPtt(request.getPtt());
            existingAddress.setCity(request.getCity());
            existingAddress.setPhoneNumber(request.getPhoneNumber());

            return addressDataRepository.save(existingAddress);
        }

        return null;
    }


}
