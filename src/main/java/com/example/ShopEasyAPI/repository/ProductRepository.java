package com.example.ShopEasyAPI.repository;

import com.example.ShopEasyAPI.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product saveProduct(Product product);
}
