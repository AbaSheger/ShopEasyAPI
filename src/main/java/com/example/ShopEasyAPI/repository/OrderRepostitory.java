package com.example.ShopEasyAPI.repository;

import com.example.ShopEasyAPI.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepostitory extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);
}
