package com.smy.shop.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.glz.enums.ResultEnum;
import com.glz.model.ResponseResult;
import com.glz.pojo.RoleMenu;
import com.glz.pojo.RoleUser;
import com.smy.shop.mapper.RoleMenuMapper;
import com.smy.shop.service.RoleMenuService;
import com.smy.shop.service.RoleUserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
@Service
public class RoleMenuServiceImpl implements RoleMenuService {
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private RoleUserService roleUserService;

    @Override
    public ResponseResult addRoleMenu(RoleMenu roleMenu) {
        int result = roleMenuMapper.insert(roleMenu);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult delete(String roleId,String menuId) {
        HashMap map = new HashMap<String,Object>();
        map.put("role_id",roleId);
        map.put("menu_id",menuId);
        int result = roleMenuMapper.deleteByMap(map);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    @Transactional
    public ResponseResult update(RoleMenu roleMenu) {
        if (roleMenu.getMenuIds().length == 0) {

        }
        for(String menuId:roleMenu.getMenuIds()){
            RoleMenu oldRoleMenu = selectByMenuIdAndByRoleId(menuId,roleMenu.getRoleId());
            System.out.println(oldRoleMenu);
            System.out.println(ObjectUtil.isEmpty(oldRoleMenu));
            if (ObjectUtil.isEmpty(oldRoleMenu)){
                addRoleMenu(new RoleMenu(roleMenu.getRoleId(), menuId));
            }else {
                delete(roleMenu.getRoleId(),menuId);
            }
        }
        return ResponseResult.success();
    }

    @Override
    public Set<RoleMenu> selectAll() {
        return roleMenuMapper.findAll();
    }

    @Override
    public RoleMenu selectByRoleId(String roleId) {
        return roleMenuMapper.findByRoleId(roleId);
    }

    @Override
    public RoleMenu selectByUserId(String userId) {
        RoleUser roleUser = roleUserService.selectByUserId(userId);
        return selectByRoleId(roleUser.getRoleId());
    }


    @Override
    public RoleMenu selectByMenuIdAndByRoleId(String menuId, String roleId) {
        RoleMenu roleMenu = roleMenuMapper.selectOne(new QueryWrapper<RoleMenu>()
                .eq("role_id", roleId)
                .eq("menu_id", menuId));
        return roleMenu;
    }


}