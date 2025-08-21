package com.example.product_api.controller;

import com.example.product_api.entity.Product;
import com.example.product_api.exception.ProductNotFoundException;
import com.example.product_api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name="Product API",description = "This is Product API")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Operation(summary="Create a Product", description="Create a product")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product)
    {
        System.out.println("Hi");
      return   ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
    }

    @Operation(summary="Get All Products", description="Returns list of products")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct()
    {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @Operation(summary="Get Product by Id", description="Returns product by Id")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable Long id)
    {
        Product product=productService.findProductById(id).orElseThrow(()-> new ProductNotFoundException(id));
        return ResponseEntity.ok(product);
    }

    @Operation(summary="Update Product by Id", description="Update product by Id")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product product)
    {
        productService.findProductById(id).orElseThrow(()-> new ProductNotFoundException(id));
        product.setId(id);
        return ResponseEntity.ok(productService.saveProduct(product));

    }

    @Operation(summary="Delete Product by Id", description="Delete product by Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id)
    {
    productService.findProductById(id).orElseThrow(()-> new ProductNotFoundException(id));
    productService.deleteProductById(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }




}
