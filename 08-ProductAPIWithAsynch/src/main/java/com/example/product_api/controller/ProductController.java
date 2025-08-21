package com.example.product_api.controller;

import com.example.product_api.entity.Product;
import com.example.product_api.exception.ProductNotFoundException;
import com.example.product_api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/async-task")
    public CompletableFuture<String> executeAsyncTask()
    {

        return productService.longRunningTask();
    }


    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product)
    {
        System.out.println("Hi");
      return   ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct()
    {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable Long id)
    {
        Product product=productService.findProductById(id).orElseThrow(()-> new ProductNotFoundException(id));
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@Valid @RequestBody Product product)
    {
        productService.findProductById(id).orElseThrow(()-> new ProductNotFoundException(id));
        product.setId(id);
        return ResponseEntity.ok(productService.saveProduct(product));

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id)
    {
    productService.findProductById(id).orElseThrow(()-> new ProductNotFoundException(id));
    productService.deleteProductById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }




}
