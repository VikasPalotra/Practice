package com.example.product_api.service;

import com.example.product_api.entity.Product;
import com.example.product_api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    //for create and update both
    @Transactional
    public  Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id)
    {
    return productRepository.findById(id);
    }

    @Transactional
    public void deleteProductById(Long id)
    {
        productRepository.deleteById(id);
    }
}
