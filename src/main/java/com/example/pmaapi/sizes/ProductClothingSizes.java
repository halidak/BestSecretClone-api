package com.example.pmaapi.sizes;

import com.example.pmaapi.product.Product;
import com.example.pmaapi.sizes.clothing.ClothingSize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductClothingSizes {

    @Id
    @SequenceGenerator(
            name = "product-csize_sequence",
            sequenceName = "product-csize_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product-csize_sequence"
    )
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private ClothingSize clothingSize;

    private int amount;
}
