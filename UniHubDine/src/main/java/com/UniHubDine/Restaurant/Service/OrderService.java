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
import com.UniHubDine.Restaurant.Model.OrderDetailDTO;
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
    public boolean placeOrder(User user, List<CartItem> cartItems) {
        Order order = new Order();
        order.setUserId(user.getUserId());
        order.setStatus("Pending"); // initial order status as "Pending"
        order.setOrderTimestamp(LocalDateTime.now());
        order.setPickupTimestamp(LocalDateTime.now().plusMinutes(20));
        int orderId = orderJdbcRepository.createOrder(order);
        
        List<OrderDetails> orderDetailsList = createOrderDetails(orderId, cartItems,user);

        boolean detailsInsertedSuccessfully = orderJdbcRepository.createOrderDetails(orderDetailsList);
        if (!detailsInsertedSuccessfully) {
            return false;
        }

        return true;
    }

    private List<OrderDetails> createOrderDetails(int orderId, List<CartItem> cartItems,User user) {
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setOrderId(orderId);
            orderDetail.setItemId(cartItem.getItemId());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getMenuItem().getPrice());
            orderDetail.setUser_id(user.getUserId());
            orderDetailsList.add(orderDetail);
        }
        return orderDetailsList;
    }
    
    public List<OrderDetailDTO> getOrderDetails(String userId) {
        return orderJdbcRepository.findOrderDetailsByUserId(userId);
    }
    public List<OrderDetailDTO> getCustomerOrderDetails(String userId) {
        return orderJdbcRepository.findCustomerOrderDetailsByUserId(userId);
    }
}
