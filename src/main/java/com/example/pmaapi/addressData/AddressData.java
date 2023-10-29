package com.example.pmaapi.addressData;

import com.example.pmaapi.order.Order;
import com.example.pmaapi.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressData {

    @Id
    @SequenceGenerator(
            name = "addressData_sequence",
            sequenceName = "addressData_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "addressData_sequence"
    )
    private Long id;
    private String phoneNumber;
    private String address;
    private String city;
    private String ptt;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id"
    )
    private User user;

    @JsonIgnore
    @OneToMany
    @JoinColumn(
            name = "addressData_id",
            referencedColumnName = "id"
    )
    private List<Order> orders;
}
