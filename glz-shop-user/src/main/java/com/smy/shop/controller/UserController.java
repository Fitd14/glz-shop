package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.User;
import com.smy.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseResult save(User user){
        return userService.insert(user);
    }


}
