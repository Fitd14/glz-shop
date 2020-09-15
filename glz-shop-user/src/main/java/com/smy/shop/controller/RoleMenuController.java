package com.smy.shop.controller;

import com.glz.enums.ResultEnum;
import com.glz.model.ResponseResult;
import com.glz.pojo.Role;
import com.glz.pojo.RoleMenu;
import com.glz.pojo.RoleUser;
import com.smy.shop.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/role/menu")
public class RoleMenuController {

    @Autowired
    private RoleMenuService roleMenuService;

    @PostMapping("/save")
    public ResponseResult save(@RequestBody RoleMenu roleMenu){
        return roleMenuService.addRoleMenu(roleMenu);
    }

    @PutMapping("/put")
    public ResponseResult put(@RequestBody RoleMenu roleMenu){
        System.out.println(roleMenu);
        return roleMenuService.update(roleMenu);
    }

    @GetMapping("/getAll")
    public ResponseResult getAll(){
        Set<RoleMenu> roleMenus = roleMenuService.selectAll();
        return new ResponseResult<Set<RoleMenu>>(ResultEnum.OK.getCode(),ResultEnum.OK.getValue(), roleMenus);
    }

    @GetMapping("/getRoleMenu/user/{userId}")
    public ResponseResult getRoleMenuByUserId(@PathVariable String userId){
        RoleMenu roleMenu = roleMenuService.selectByUserId(userId);
        return new ResponseResult(ResultEnum.OK.getCode(),ResultEnum.OK.getValue(),roleMenu);
    }
    @GetMapping("/getRoleMenu/role/{roleId}")
    public ResponseResult getRoleMenuByRoleId(@PathVariable String roleId){
        RoleMenu roleMenu = roleMenuService.selectByRoleId(roleId);
        return new ResponseResult(ResultEnum.OK.getCode(),ResultEnum.OK.getValue(),roleMenu);
    }
}
