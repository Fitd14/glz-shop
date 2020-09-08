package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.Role;
import com.glz.pojo.RoleMenu;
import com.smy.shop.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role/menu")
public class RoleMenuController {

    @Autowired
    private RoleMenuService roleMenuService;

    @PostMapping("/save")
    public ResponseResult save(RoleMenu roleMenu){
        return roleMenuService.addRoleMenu(roleMenu);
    }

    @PutMapping("/put")
    public ResponseResult put(RoleMenu roleMenu){
        return roleMenuService.update(roleMenu);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable long id){
        return roleMenuService.delete(id);
    }
}
