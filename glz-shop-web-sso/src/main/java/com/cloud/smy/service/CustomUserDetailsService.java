package com.cloud.smy.service;

import cn.hutool.core.util.ObjectUtil;
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

    @Reference
    RoleMenuService roleMenuService;

    @Reference
    MemberService memberService;

    @Override
    @Cacheable(cacheNames="spring:security:userDetails", key="#username")
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.selectByUsername(username);

        String userName = null;
        String password = null;
        String uid = user.getId();
        String roleId = null;

        if(ObjectUtils.isEmpty(user)){
            Member byUsername = memberService.findByUsername(username);
            if(ObjectUtils.isEmpty(byUsername)){
                throw new UsernameNotFoundException("用户名或密码错误");
            }
            userName = byUsername.getUsername();
            password = byUsername.getPassword();
            uid = byUsername.getId();
        }

        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        RoleUser roleUser = roleUserService.selectByUserId(uid);

        if(ObjectUtils.isEmpty(roleUser)){
            grantedAuthoritySet.add(new SimpleGrantedAuthority("ROLE_visitor"));
            grantedAuthoritySet.add(new SimpleGrantedAuthority("desk:show"));
            return new org.springframework.security.core.userdetails.User(userName,password,
                    true,true,true,true,grantedAuthoritySet);
        }


        roleId = roleUser.getRoleId();
        Role role = roleService.selectById(roleId);

        grantedAuthoritySet.add(new SimpleGrantedAuthority("ROLE_"+ role.getCode()));

        RoleMenu roleMenu = roleMenuService.selectByRoleId(roleId);

        for (Permission permission : roleMenu.getMenuAll()){
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
    public Set<Permission> allSysPermissions() {
        Set<Permission> permissions = permissionService.selectAll();
        return permissions;
    }
}
