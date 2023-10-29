package com.example.pmaapi.order.response;

import com.example.pmaapi.addressData.AddressData;
import com.example.pmaapi.order.PaymentMethod;
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
public class OrderResponse {
    private Date orderDate;
    private PaymentMethod paymentMethod;
    private float fullPrice;
    private AddressData addressData;
    private List<Product> products;
}
