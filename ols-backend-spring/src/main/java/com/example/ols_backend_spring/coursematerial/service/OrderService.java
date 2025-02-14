package com.example.ols_backend_spring.coursematerial.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.ols_backend_spring.coursematerial.entity.Course;
import com.example.ols_backend_spring.coursematerial.entity.Order;
import com.example.ols_backend_spring.coursematerial.entity.User;
import com.example.ols_backend_spring.coursematerial.repository.CourseRepository;
import com.example.ols_backend_spring.coursematerial.repository.OrderRepository;
import com.example.ols_backend_spring.coursematerial.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(()-> new RuntimeException("Oder not found by id"));
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    // creating an order for user who purchases a course
    public Order createOrderForUser(Long userId, Long courseId, String orderStatus){
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Course> courseOptional = courseRepository.findById(courseId);

        if(userOptional.isPresent() && courseOptional.isPresent()){
            User user = userOptional.get();
            Course course = courseOptional.get();

            Order order = new Order();
            order.setUser(user);
            order.setCourse(course);
            order.setOrderStatus(orderStatus);
            order.setOrderTotalAmount(course.getCoursePrice());

            return orderRepository.save(order);
        }else {
            throw new RuntimeException("User or course not found by id's");
        }
    }


    // update orderStatus
    public void updateOrder(Long orderId, String newOrderStatus){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(newOrderStatus);
        orderRepository.save(order);
    }
}