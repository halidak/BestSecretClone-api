package com.example.pmaapi.order.response;

import com.example.pmaapi.order.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Date orderDate;
    private PaymentMethod paymentMethod;
    private float fullPrice;
}
