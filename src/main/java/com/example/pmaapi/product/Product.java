package com.example.pmaapi.product;

import com.example.pmaapi.category.Category;
import com.example.pmaapi.orderDetails.OrderDetails;
import com.example.pmaapi.product.productImages.ProductImages;
import com.example.pmaapi.sizes.ProductClothingSizes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    private String name;
    private float price;
    private String description;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "category_id",
            referencedColumnName = "id"
    )
    private Category category;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id"
    )
    private List<ProductImages> images;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<ProductClothingSizes> productClothingSizes;

    @JsonIgnore
    @OneToMany
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id"
    )
    private List<OrderDetails> orderDetails;

}
