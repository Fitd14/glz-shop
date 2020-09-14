package com.cloud.smy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/user")
public class UserController {

    @RequestMapping("/login")
    public String get(){
        return "hello";
    }
}
