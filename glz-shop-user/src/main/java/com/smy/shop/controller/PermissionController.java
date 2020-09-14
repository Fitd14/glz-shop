package com.smy.shop.controller;

import com.glz.enums.ResultEnum;
import com.glz.model.ResponseResult;
import com.glz.pojo.Permission;
import com.smy.shop.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/menu")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/save")
    public ResponseResult save(@RequestBody Permission permission){
        return permissionService.addPermission(permission);
    }

    @PutMapping("/put")
    public ResponseResult update(@RequestBody Permission permission){
        return permissionService.update(permission);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable String id){
        return permissionService.deleteById(id);
    }
    
    @GetMapping("/getAll")
    public ResponseResult getAll(){
        Set<Permission> permissions = permissionService.selectAll();
        return new ResponseResult<Set<Permission>>(ResultEnum.OK.getCode(),ResultEnum.OK.getValue(),permissions);
    }
}
