package com.example.ShopEasyAPI.services;


import com.example.ShopEasyAPI.entity.Order;
import com.example.ShopEasyAPI.repository.OrderRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepostitory orderRepostitory;

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepostitory.findByUserId(userId);
    }

    public Order saveOrder(Order order) {
        return orderRepostitory.save(order);
    }

}