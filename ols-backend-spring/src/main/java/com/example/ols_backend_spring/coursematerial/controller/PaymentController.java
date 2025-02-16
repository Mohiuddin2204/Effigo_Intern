package com.example.ols_backend_spring.coursematerial.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ols_backend_spring.coursematerial.entity.Payment;
import com.example.ols_backend_spring.coursematerial.service.PaymentService;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping("/auth/admin/api/payments")
    public List<Payment> getAllPayments(){
        return paymentService.getAllPayments();
    }

    @GetMapping("/auth/user/api/payments/{payId}")
    public Payment getPaymentById(@PathVariable Long payId){
        return paymentService.getPaymentById(payId);
    }

    @PostMapping("/auth/admin/api/payments")
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment){
        Payment payment1 = paymentService.createPayment(payment);
        return ResponseEntity.ok(payment1);
    }

//Cant delete payment first , as it is a process paid: DELETE ORDER=>then automatically respective payment deleted

    
    @DeleteMapping("/auth/admin/api/payments/{payId}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long payId){
        paymentService.deletePayment(payId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/auth/user/api/payments/process")
    public ResponseEntity<Payment> processPayment(@RequestParam Long orderId, @RequestParam String payMethod, @RequestParam String payStatus){
        Payment createdPayment = paymentService.processPayment(orderId, payMethod, payStatus);
        return ResponseEntity.ok(createdPayment);
    }
}
