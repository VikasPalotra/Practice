package com.example.product_api.controller;

import com.example.product_api.entity.Product;
import com.example.product_api.exception.ProductNotFoundException;
import com.example.product_api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product)
    {
        System.out.println("Hi");
      return   ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
    }
    @GetMapping
    public ResponseEntity<Page<Product>> getAllProduct(Pageable pageable)
    {
        return ResponseEntity.ok(productService.getAllProducts(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable Long id)
    {
        Product product=productService.findProductById(id).orElseThrow(()-> new ProductNotFoundException(id));
        return ResponseEntity.ok(product);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Product>> filterProducts(@RequestParam String title, Pageable pageable)
    {
        return ResponseEntity.ok(productService.filterProduct(title,pageable));
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
