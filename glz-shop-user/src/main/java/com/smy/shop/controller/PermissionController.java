package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.Permission;
import com.smy.shop.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/save")
    public ResponseResult save(Permission permission){
        return permissionService.addPermission(permission);
    }

    @PutMapping("/update")
    public ResponseResult update(Permission permission){
        return permissionService.update(permission);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable long id){
        return permissionService.deleteById(id);
    }
}
