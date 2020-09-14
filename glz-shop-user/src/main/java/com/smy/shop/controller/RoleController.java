package com.smy.shop.controller;

import com.glz.enums.ResultEnum;
import com.glz.model.ResponseResult;
import com.glz.pojo.Role;
import com.smy.shop.service.RoleService;
import org.apache.commons.codec.language.RefinedSoundex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/save")
    public ResponseResult save(@RequestBody Role role){
        return roleService.save(role);
    }

    @PutMapping("/put")
    public ResponseResult put(@RequestBody Role role){
        return roleService.update(role);
    }

    @DeleteMapping("/{id}")
    public ResponseResult delete(@PathVariable String id){
        System.out.println(id);
        return roleService.deleteById(id);
    }
    @GetMapping("/getAll")
    public ResponseResult getAll(){
        List<Role> roles = roleService.selectAll();
        return new ResponseResult(ResultEnum.OK.getCode(),ResultEnum.OK.getValue(),roles);
    }
    @GetMapping("/get/{id}")
    public ResponseResult get(@PathVariable String id ){
        Role role = roleService.selectById(id);
        return new ResponseResult(ResultEnum.OK.getCode(),ResultEnum.OK.getValue(),role);
    }
}
