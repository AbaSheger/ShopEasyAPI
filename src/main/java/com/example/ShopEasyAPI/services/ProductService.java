package com.example.ShopEasyAPI.services;

import com.example.ShopEasyAPI.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.ShopEasyAPI.entity.Product;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

  public Product saveProduct(Product product) {
    return productRepository.saveProduct(product);
  }

}
