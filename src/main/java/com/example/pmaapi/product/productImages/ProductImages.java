package com.example.pmaapi.product.productImages;

import com.example.pmaapi.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductImages {
    @Id
    @SequenceGenerator(
            name = "productImages_sequence",
            sequenceName = "productImages_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "productImages_sequence"
    )
    private Long id;
    private String photo;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id"
    )
    private Product product;
}
