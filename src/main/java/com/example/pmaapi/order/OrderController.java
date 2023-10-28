package com.example.pmaapi.order;


import com.example.pmaapi.order.request.CreateOrder;
import com.example.pmaapi.order.response.OrderResponse;
import com.example.pmaapi.order.response.UserOrders;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/all")
    public Iterable<UserOrders> getAllOrders(@RequestHeader("Authorization") String token) {
        return orderService.getOrders(token);
    }

    @GetMapping("/get-by-id/{orderId}")
    public ResponseEntity<UserOrders> getById(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.getOrderById(orderId));
    }
}
