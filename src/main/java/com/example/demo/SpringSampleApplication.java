package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSampleApplication {
    
    public static void main(String[] args) {
        
        // マージテスト2
        System.out.println("feature-bのソースです。");
        SpringApplication.run(SpringSampleApplication.class, args);
    }
    
}
