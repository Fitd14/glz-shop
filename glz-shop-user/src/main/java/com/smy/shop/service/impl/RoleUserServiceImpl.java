package com.smy.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glz.model.ResponseResult;
import com.glz.pojo.RoleUser;
import com.smy.shop.mapper.RoleUserMapper;
import com.smy.shop.service.RoleUserService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public ResponseResult deleteById(long id) {
        int result = roleUserMapper.deleteById(id);
        if(result > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public List<RoleUser> selectAll() {
        return roleUserMapper.selectList(new QueryWrapper<RoleUser>());
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
    public RoleUser selectByUserId(long uid) {
        return roleUserMapper.selectOne(new QueryWrapper<RoleUser>().eq("user_id",uid));
    }
}
