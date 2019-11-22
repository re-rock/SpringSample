package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSampleApplication {

    public static void main(String[] args) {

        // マージテスト
        System.out.println("feature-aのソースです。");
        SpringApplication.run(SpringSampleApplication.class, args);
    }

}
