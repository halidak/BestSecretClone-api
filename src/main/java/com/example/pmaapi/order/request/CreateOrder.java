package com.example.pmaapi.order.request;

import com.example.pmaapi.order.PaymentMethod;
import com.example.pmaapi.orderDetails.request.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrder {
    private PaymentMethod paymentMethod;
    private List<OrderItem> orderItems;
}
