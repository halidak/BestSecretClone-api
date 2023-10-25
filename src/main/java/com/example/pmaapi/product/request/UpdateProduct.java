package com.example.pmaapi.product.request;

import com.example.pmaapi.product.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateProduct {
    private String name;
    private float price;
    private String description;
    private Gender gender;
}
