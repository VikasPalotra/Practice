package com.example.product_api.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.product_api.entity.Product;
import com.example.product_api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    @CacheEvict(value = "products",allEntries = true)


    @Async
    public CompletableFuture<String> longRunningTask()
    {
        System.out.println("Long running task");
        try {
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return CompletableFuture.completedFuture("Task Completed");
    }

    //for create and update both
    @Transactional
    public  Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Cacheable(value = "products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Long id)
    {
    return productRepository.findById(id);
    }

    @CacheEvict(value = "products",allEntries = true)
    @Transactional
    public void deleteProductById(Long id)
    {
        productRepository.deleteById(id);
    }
}
