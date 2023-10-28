package com.example.pmaapi.sizes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductClothingSizesRepository extends JpaRepository<ProductClothingSizes, Long> {
    ProductClothingSizes findByProduct_IdAndClothingSize_Id(Long productId, Long clothingSizeId);
}
