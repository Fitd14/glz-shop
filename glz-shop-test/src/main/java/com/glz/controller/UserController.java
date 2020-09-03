package com.glz.controller;


import com.glz.model.ResponseResult;
import com.glz.pojo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value="登陆模块")
@RestController
public class UserController {

    @ApiOperation(value="新增用户",notes = "返回通用结果集")
    @PostMapping("/user/create")
    public ResponseResult<User> createUser(){
        return ResponseResult.success();
    }
}

