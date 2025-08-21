package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

@GetMapping("/")
    public String sayHello()
    {
        System.out.println("sayHello method");
        return "Hello Spring Boot app";
    }
}
