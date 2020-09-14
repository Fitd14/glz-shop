package com.smy.shop.controller;

import com.glz.enums.ResultEnum;
import com.glz.model.ResponseResult;
import com.glz.model.RoleUserDTO;
import com.glz.pojo.RoleUser;
import com.smy.shop.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/role/user")
public class RoleUserController {

    @Autowired
    private RoleUserService roleUserService;

    @PostMapping("/save")
    public ResponseResult save(@RequestBody RoleUser roleUser){
        //System.out.println(roleUser);
        return roleUserService.save(roleUser);
    }

    @PutMapping("/put")
    public ResponseResult put(@RequestBody RoleUser roleUser){
        return roleUserService.update(roleUser);
    }

    @DeleteMapping("/{roleId}/{userId}")
    public ResponseResult delete(@PathVariable("roleId") String roleId, @PathVariable("userId") String userId) {
        return roleUserService.delete(roleId,userId);
    }

    @GetMapping("/getAll")
    public ResponseResult getAll(){
        Set<RoleUserDTO> roleUsers = roleUserService.selectAll();
        return new ResponseResult<Set<RoleUserDTO>>(ResultEnum.OK.getCode(),ResultEnum.OK.getValue(), roleUsers);
    }

    @GetMapping("/get")
    public ResponseResult get(RoleUser roleUser){
        RoleUser oneRoleUser = roleUserService.selectByUserIdAndRoleId(roleUser);
        return new ResponseResult<RoleUser>(ResultEnum.OK.getCode(),ResultEnum.OK.getValue(), oneRoleUser);
    }
}
