package com.example.pmaapi.addressData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddressDataRepository extends JpaRepository<AddressData, Long> {
    @Query("SELECT a FROM AddressData a WHERE a.user.id = :userId")
    List<AddressData> findAddressesByUserId(Long userId);

    Optional<AddressData> findById(Long id);

}
