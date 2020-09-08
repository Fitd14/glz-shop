package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.Role;
import com.glz.pojo.RoleUser;
import com.smy.shop.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role/user")
public class RoleUserController {

    @Autowired
    private RoleUserService roleUserService;

    @PostMapping("/save")
    public ResponseResult save(RoleUser roleUser){
        return roleUserService.save(roleUser);
    }

    @PutMapping("/put")
    public ResponseResult put(RoleUser roleUser){
        return roleUserService.update(roleUser);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable long id){
        return roleUserService.deleteById(id);
    }
}
