package com.example.pmaapi.product;

import com.example.pmaapi.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategoryId (Long categoryId);

    List<Product> findByCategoryIdAndGender(Long categoryId, Gender gender);

    @Query("SELECT DISTINCT p FROM Product p " +
            "LEFT JOIN FETCH p.productClothingSizes pcs " +
            "LEFT JOIN FETCH pcs.clothingSize " +
            "WHERE p.id = :productId")
    Product findProductWithSizes(@Param("productId") Long productId);

}
