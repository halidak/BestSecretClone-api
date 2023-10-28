package com.example.pmaapi.orderDetails.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    private Long productId;
    private int quantity;
    private Long clothingSizeId;
}
