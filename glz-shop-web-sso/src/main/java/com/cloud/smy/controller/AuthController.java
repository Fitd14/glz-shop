package com.cloud.smy.controller;

import com.smy.shop.service.UserService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Reference
    UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/user")
    public String getUser(){
        return "User";
    }

}
