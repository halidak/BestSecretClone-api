package com.example.pmaapi.sizes.clothing;

import com.example.pmaapi.sizes.ProductClothingSizes;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClothingSize {

    @Id
    @SequenceGenerator(
            name = "clothingSize_sequence",
            sequenceName = "clothingSize_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "clothingSize_sequence"
    )
    private Long id;
    private String name;

    @OneToMany(mappedBy = "clothingSize")
    Set<ProductClothingSizes> productClothingSizes;
}
