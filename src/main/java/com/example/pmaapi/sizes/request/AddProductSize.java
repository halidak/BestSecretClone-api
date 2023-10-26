package com.example.pmaapi.sizes.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddProductSize {
    private Long productId;
    private Long clothingSizeId;
    private int amount;
}
