package com.example.pmaapi.sizes.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductSizeDTO {
    private Long id;
    private Long clothingSizeId;
    private String clothingSizeName;
    private int amount;
}
