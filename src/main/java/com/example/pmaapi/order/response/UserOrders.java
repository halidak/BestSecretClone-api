package com.example.pmaapi.order.response;

import com.example.pmaapi.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserOrders {
    private Long orderId;
    private Date orderDate;
    private String paymentMethod;
    private float fullPrice;
    private List<Product> products;
}
