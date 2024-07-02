package com.example.pmaapi.sizes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface ProductClothingSizesRepository extends JpaRepository<ProductClothingSizes, Long> {
    ProductClothingSizes findByProduct_IdAndClothingSize_Id(Long productId, Long clothingSizeId);
    @Query("SELECT pcs FROM ProductClothingSizes pcs WHERE pcs.product.id = :productId")
    Set<ProductClothingSizes> findByProduct_Id(@Param("productId") Long productId);
}
