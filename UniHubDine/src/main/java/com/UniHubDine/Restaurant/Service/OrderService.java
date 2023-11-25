package com.UniHubDine.Restaurant.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.UniHubDine.Restaurant.Dao.OrderJdbcRepository;
import com.UniHubDine.Restaurant.Model.CartItem;
import com.UniHubDine.Restaurant.Model.Order;
import com.UniHubDine.Restaurant.Model.OrderDetails;
import com.UniHubDine.Restaurant.Model.User;

@Service
public class OrderService {

    private final OrderJdbcRepository orderJdbcRepository;

    @Autowired
    public OrderService(OrderJdbcRepository orderJdbcRepository) {
        this.orderJdbcRepository = orderJdbcRepository;
    }

    @Transactional
    public void placeOrder(User user, List<CartItem> cartItems) {
        // Create a new order
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setStatus("Pending"); // Set the initial order status as "Pending"
        order.setOrderTimestamp(LocalDateTime.now());
        order.setPickupTimestamp(LocalDateTime.now().plusMinutes(20));
        // Create the order and retrieve the generated order_id
        int orderId = orderJdbcRepository.createOrder(order);
        
        // Create order details
        List<OrderDetails> orderDetailsList = createOrderDetails(orderId, cartItems,user);

        // Save the order details to the database
        orderJdbcRepository.createOrderDetails(orderDetailsList);
    }

    private List<OrderDetails> createOrderDetails(int orderId, List<CartItem> cartItems,User user) {
        // Create OrderDetails objects from cart items
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setOrderId(orderId);
            orderDetail.setItemId(cartItem.getItemId());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getMenuItem().getPrice());
            orderDetail.setUser_id(user.getUserId());
            // ...
            orderDetailsList.add(orderDetail);
        }
        return orderDetailsList;
    }
    
}
