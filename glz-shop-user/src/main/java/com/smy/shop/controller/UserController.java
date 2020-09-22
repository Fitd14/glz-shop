package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.User;
import com.smy.shop.service.UserService;
import com.smy.shop.utils.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public ResponseResult save(User user){
        System.out.println(user);
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
    public ResponseResult delete(@PathVariable String id){
        return userService.delete(id);
    }
    @GetMapping("/all")
    public ResponseResult getAll(){
        return userService.selectAll();
    }

    @GetMapping("{id}")
    public ResponseResult get(@PathVariable String id){
        return userService.selectById(id);
    }

    @PutMapping("/put")
    public ResponseResult modify(@RequestBody User user){
        return userService.update(user);
    }

    @GetMapping("/getInfo")
    public ResponseResult getInfo(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String username = JWTTokenUtil.getUsernameFromToken(token);
        User user = userService.selectByUsername(username);
        return new ResponseResult("200","成功",user);
    }

}
