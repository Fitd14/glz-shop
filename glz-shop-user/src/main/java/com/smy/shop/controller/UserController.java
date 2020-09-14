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
    public ResponseResult save(@RequestBody User user){
        return userService.insert(user);
    }

    @PutMapping("/put/password")
    public ResponseResult updatePassword(@RequestBody String username,@RequestBody String password){
        return userService.updatePasswordByUsername(username,password);
    }

    @PutMapping("/put/fp")
    public ResponseResult forgotPassword(){
        return new ResponseResult();
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable Long id){
        return userService.delete(id);
    }
    @GetMapping("/all")
    public ResponseResult getAll(){
        return userService.selectAll();
    }

    @GetMapping("{id}")
    public ResponseResult get(@PathVariable Long id){
        return userService.selectById(id);
    }

    @PutMapping("/put")
    public ResponseResult modify(@RequestBody User user){
        return userService.update(user);
    }

}
