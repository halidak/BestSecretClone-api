package com.example.pmaapi.order;

import com.example.pmaapi.config.JwtService;
import com.example.pmaapi.order.request.CreateOrder;
import com.example.pmaapi.order.response.OrderResponse;
import com.example.pmaapi.orderDetails.OrderDetails;
import com.example.pmaapi.orderDetails.OrderDetailsRepository;
import com.example.pmaapi.orderDetails.request.OrderItem;
import com.example.pmaapi.product.Product;
import com.example.pmaapi.product.ProductRepository;
import com.example.pmaapi.sizes.ProductClothingSizes;
import com.example.pmaapi.sizes.ProductClothingSizesRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final ProductRepository productRepository;
    private final JwtService jwtService;
    private final ProductClothingSizesRepository productClothingSizesRepository;

    @Transactional
    public OrderResponse createOrder(CreateOrder request, String token){
        var user = jwtService.getUserFromToken(token);
        Order order = new Order();
        //trenutni datum
        order.setOrderDate(new Date());

        order.setPaymentMethod(request.getPaymentMethod());
        order.setUser(user);

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        float totalPrice = 0.0f;

        for (OrderItem orderItem : request.getOrderItems()) {
            Long productId = orderItem.getProductId();
            int quantity = orderItem.getQuantity();
            Long clothingSizeId = orderItem.getClothingSizeId();

            Product product = productRepository.findById(productId).orElse(null);

            ProductClothingSizes productClothingSizes =
                    productClothingSizesRepository.findByProduct_IdAndClothingSize_Id(productId, clothingSizeId);

            if (productClothingSizes != null) {
                int currentAmount = productClothingSizes.getAmount();
                if (currentAmount >= quantity) {
                    productClothingSizes.setAmount(currentAmount - quantity);
                    productClothingSizesRepository.save(productClothingSizes);

                    OrderDetails orderDetails = new OrderDetails();
                    orderDetails.setAmount(quantity);
                    orderDetails.setProduct(productClothingSizes.getProduct());
                    orderDetails.setProductClothingSize(productClothingSizes);
                    orderDetails.setProduct(productClothingSizes.getProduct());
                    orderDetails.setOrder(order);

                    orderDetailsList.add(orderDetails);

                    totalPrice += productClothingSizes.getProduct().getPrice() * quantity;

                }
                else {
                    throw new RuntimeException("Nedovoljna količina proizvoda.");
                }
            } else {
                throw new EntityNotFoundException("Proizvod ili veličina odjeće nije pronađena.");
            }
        }
            order.setOrderDetails(orderDetailsList);
            order.setFullPrice(totalPrice);
            orderRepository.save(order);

            for(OrderDetails orderDetails : orderDetailsList){
                orderDetails.setOrder(order);
                orderDetails.setProduct(orderDetails.getProductClothingSize().getProduct());
                orderDetails.setProductClothingSize(orderDetails.getProductClothingSize());
                orderDetails.setAmount(orderDetails.getAmount());
                orderDetailsRepository.save(orderDetails);
            }

        OrderResponse orderResponse = OrderResponse.builder()
                .orderDate(order.getOrderDate())
                .paymentMethod(request.getPaymentMethod())
                .fullPrice(totalPrice)
                .build();

        return orderResponse;
    }

    //get orders from user


}