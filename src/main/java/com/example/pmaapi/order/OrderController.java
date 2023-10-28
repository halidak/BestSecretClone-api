package com.example.pmaapi.order;


import com.example.pmaapi.order.request.CreateOrder;
import com.example.pmaapi.order.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create")
    public OrderResponse createOrder(@RequestBody CreateOrder request, @RequestHeader("Authorization") String token) {
        return orderService.createOrder(request, token);
    }
}
