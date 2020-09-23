package com.smy.shop.controller;

import com.glz.enums.ResultEnum;
import com.glz.model.ResponseResult;
import com.glz.pojo.Permission;
import com.glz.pojo.RoleMenu;
import com.glz.pojo.RoleUser;
import com.glz.pojo.User;
import com.smy.shop.service.PermissionService;
import com.smy.shop.service.RoleMenuService;
import com.smy.shop.service.RoleUserService;
import com.smy.shop.service.UserService;
import com.smy.shop.utils.JWTTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/menu")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleUserService roleUserService;

    @Autowired
    private RoleMenuService roleMenuService;

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

    @GetMapping("/{id}")
    public ResponseResult getPermission(@PathVariable String id){
        Permission permission = permissionService.selectById(id);
        return new ResponseResult<>(ResultEnum.OK.getCode(),ResultEnum.OK.getValue(),permission);
    }

    @GetMapping("/getUserMenu")
    public ResponseResult getUserMenu(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String username = JWTTokenUtil.getUsernameFromToken(token);
        User user = userService.selectByUsername(username);
        RoleMenu roleMenu = roleMenuService.selectByUserId(user.getId());

        Set<Permission> permissions = subjectMapper(roleMenu.getMenuAll());
        roleMenu.setMenuAll(permissions);

        return new ResponseResult<Set<Permission>>(ResultEnum.OK.getCode(),ResultEnum.OK.getValue(),roleMenu.getMenuAll());
    }

    private Set<Permission> subjectMapper(Set<Permission> list){
        Set<Permission> permissions = new HashSet<>();
        for (Permission permission: list){
            if ("0".equals(permission.getPid())){
                List<Permission> subjectPermission = permissionService.selectByPid(permission.getId());
                HashSet<Permission> permissionHashSet = new HashSet<>(subjectPermission);
                permission.setPermissions(permissionHashSet);
                permissions.add(permission);
            }
        }
        return permissions;
    }
}
