package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.User;
import com.smy.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseResult save(User user){
        return userService.insert(user);
    }

    @PutMapping("/put/password")
    public ResponseResult updatePassword(String username,String password){
        return userService.updatePasswordByUsername(username,password);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        return userService.delete(id);
    }

    @GetMapping("/all")
    public ResponseResult getAll(){
        return userService.selectAll();
    }

}
