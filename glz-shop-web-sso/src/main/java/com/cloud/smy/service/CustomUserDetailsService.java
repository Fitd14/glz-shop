package com.cloud.smy.service;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.glz.pojo.Permission;
import com.glz.pojo.User;
import com.smy.shop.service.PermissionService;
import com.smy.shop.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Reference
    UserService userService;

    @Reference
    PermissionService permissionService;

    @Override
    @Cacheable(cacheNames="spring:security:userDetails", key="#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectByUsername(username);
        if(!ObjectUtils.isNotNull(user)){
            throw new UsernameNotFoundException("用户名或密码错误");
        }


        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),true,true,true,true,null);
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
