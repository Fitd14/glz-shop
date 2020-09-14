package com.smy.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glz.model.ResponseResult;
import com.glz.model.RoleUserDTO;
import com.glz.pojo.RoleUser;
import com.smy.shop.mapper.RoleUserMapper;
import com.smy.shop.service.RoleUserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Component
@Service
public class RoleUserServiceImpl implements RoleUserService {

    @Autowired
    private RoleUserMapper roleUserMapper;

    @Override
    public ResponseResult save(RoleUser roleUser) {
        int result = roleUserMapper.insert(roleUser);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult delete(String roleId,String userId) {
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("role_id",roleId);
        condition.put("user_id",userId);
        int result = roleUserMapper.deleteByMap(condition);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public Set<RoleUserDTO> selectAll() {
        return roleUserMapper.findAll();
    }

    @Override
    public ResponseResult update(RoleUser roleUser) {
        int result = roleUserMapper.updateById(roleUser);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public RoleUser selectByUserIdAndRoleId(RoleUser roleUser) {
        return roleUserMapper.selectOne(new QueryWrapper<RoleUser>().eq("user_id",roleUser.getUserId())
                .eq("role_id",roleUser.getRoleId()));
    }

    @Override
    public RoleUser selectByUserId(String UserId) {
        return roleUserMapper.selectOne(new QueryWrapper<RoleUser>().eq("user_id",UserId));
    }
}
