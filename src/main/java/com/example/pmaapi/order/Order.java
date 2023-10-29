package com.example.pmaapi.order;

import com.example.pmaapi.addressData.AddressData;
import com.example.pmaapi.orderDetails.OrderDetails;
import com.example.pmaapi.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @SequenceGenerator(
            name = "order_sequence",
            sequenceName = "order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_sequence"
    )
    private Long id;
    private Date orderDate;
    private float fullPrice;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "order_id",
            referencedColumnName = "id"
    )
    private List<OrderDetails> orderDetails;

    @ManyToOne
    @JoinColumn(
            name = "addressData_id",
            referencedColumnName = "id"
    )
    private AddressData addressData;

}
