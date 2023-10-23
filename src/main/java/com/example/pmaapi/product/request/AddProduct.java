package com.example.pmaapi.product.request;

import com.example.pmaapi.product.Gender;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddProduct {
    private String name;
    private float price;
    private String description;
    private Gender gender;
    private Long categoryId;
    private List<String> imageUrls;
}
