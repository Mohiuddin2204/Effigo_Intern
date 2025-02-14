package com.example.ols_backend_spring.coursematerial.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ols_backend_spring.coursematerial.entity.Order;
import com.example.ols_backend_spring.coursematerial.service.OrderService;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/auth/admin/api/orders")
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/auth/user/api/orders/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId){
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/auth/user/api/orders")
    public Order createOrder(@RequestBody Order orderData){
        return orderService.createOrder(orderData);
    }

    @DeleteMapping("/auth/user/api/orders/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId){
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/auth/user/{userId}/createOrder/{courseId}")
    public ResponseEntity<Order> createOrderForUser(@PathVariable Long userId, @PathVariable Long courseId, @RequestParam String orderStatus){
        Order createdOrder = orderService.createOrderForUser(userId, courseId, orderStatus);
        return ResponseEntity.ok(createdOrder);
    }

    @PatchMapping("/auth/user/{orderId}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable Long orderId, @RequestParam String orderStatus){
        orderService.updateOrder(orderId, orderStatus);
        return ResponseEntity.noContent().build();
    }
}