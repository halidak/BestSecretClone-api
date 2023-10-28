package com.example.pmaapi.orderDetails;

import com.example.pmaapi.order.Order;
import com.example.pmaapi.product.Product;
import com.example.pmaapi.sizes.ProductClothingSizes;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetails {

    @Id
    @SequenceGenerator(
            name = "orderDetails_sequence",
            sequenceName = "orderDetails_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "orderDetails_sequence"
    )
    private Long id;

    private int amount;

    @ManyToOne
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "id"
    )
    private Order order;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "id"
    )
    private Product product;

    @ManyToOne
    @JoinColumn(
            name = "product_csize_id",
            referencedColumnName = "id"
    )
    private ProductClothingSizes productClothingSize;

}
