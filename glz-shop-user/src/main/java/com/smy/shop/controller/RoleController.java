package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.Role;
import com.smy.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/save")
    public ResponseResult save(Role role){
        return roleService.save(role);
    }

    @PutMapping("/put")
    public ResponseResult put(Role role){
        return roleService.update(role);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable long id){
        return roleService.deleteById(id);
    }
}
