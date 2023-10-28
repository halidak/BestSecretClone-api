package com.example.pmaapi.sizes;

import com.example.pmaapi.orderDetails.OrderDetails;
import com.example.pmaapi.product.Product;
import com.example.pmaapi.sizes.clothing.ClothingSize;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    private ClothingSize clothingSize;

    private int amount;

    @OneToMany
    @JoinColumn(
            name = "product_csize_id",
            referencedColumnName = "id"
    )
    private List<OrderDetails> orderDetails;
}
