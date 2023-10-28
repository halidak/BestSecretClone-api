package com.example.pmaapi.order;

import com.example.pmaapi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findAllByUser(User user);
}
