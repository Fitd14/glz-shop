package com.cloud.smy.service;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.glz.pojo.*;
import com.smy.shop.service.*;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Reference
    UserService userService;

    @Reference
    PermissionService permissionService;

    @Reference
    RoleService roleService;

    @Reference
    RoleUserService roleUserService;

    @Override
    @Cacheable(cacheNames="spring:security:userDetails", key="#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectByUsername(username);

        if(ObjectUtils.isEmpty(user)){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        RoleUser roleUser = roleUserService.selectByUserId(user.getId());

        if(ObjectUtils.isEmpty(roleUser)){
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                    true,true,true,true,grantedAuthoritySet);
        }
        Role role = roleService.selectById(roleUser.getRoleId());

        if(ObjectUtils.isEmpty(role)){
            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                    true,true,true,true,grantedAuthoritySet);
        }
        grantedAuthoritySet.add(new SimpleGrantedAuthority("ROLE_"+ role.getCode()));

        List<Permission> permissions = permissionService.selectByRoleId(role.getId());

        for (Permission permission : permissions){
            grantedAuthoritySet.add(new SimpleGrantedAuthority(permission.getName()+":"+permission.getValue()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
                true,true,true,true,grantedAuthoritySet);

    }

    /**
     * 加载权限表中所有权限
     */
    // TODO 在权限变更时需要清空对应缓存
    @Cacheable(cacheNames="spring:security", key="'allSysPermissions'")
    public List<Permission> allSysPermissions() {
        List<Permission> permissions = permissionService.selectAll();
        return permissions;
    }
}
