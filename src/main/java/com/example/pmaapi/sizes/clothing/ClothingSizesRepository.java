package com.example.pmaapi.sizes.clothing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClothingSizesRepository extends JpaRepository<ClothingSize, Long> {
    @Query("SELECT DISTINCT cs FROM ClothingSize cs " +
            "JOIN ProductClothingSizes pcs ON cs.id = pcs.clothingSize.id " +
            "WHERE pcs.product.id = :productId " +
            "AND pcs.amount > 0")
    List<ClothingSize> findDistinctAvailableClothingSizesForProduct(Long productId);
}

