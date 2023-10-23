package com.example.pmaapi.product.response;

import com.example.pmaapi.category.Category;
import com.example.pmaapi.product.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetProducts {
    private Long id;
    private String name;
    private float price;
    private String description;
    private Gender gender;
    private Category category;
}
