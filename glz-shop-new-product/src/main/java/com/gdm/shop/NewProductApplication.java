package com.gdm.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.gdm.shop.mapper")
public class NewProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewProductApplication.class,args);
    }
}
