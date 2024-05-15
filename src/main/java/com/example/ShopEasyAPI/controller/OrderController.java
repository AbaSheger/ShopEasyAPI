package com.example.ShopEasyAPI.controller;


import com.example.ShopEasyAPI.entity.Order;
import com.example.ShopEasyAPI.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping ("/{userId")
    @PreAuthorize("hasRole('USER')")
    public List<Order> getUserOrders(@PathVariable Long userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }


}
