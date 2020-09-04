package com.smy.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.glz.constant.HttpStatus;
import com.glz.model.ResponseResult;
import com.glz.pojo.User;
import com.smy.shop.mapper.UserMapper;
import com.smy.shop.service.UserService;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult insert(User user) {
        int row = userMapper.insert(user);
        if(row > 0){
            return ResponseResult.success();
        }
        log.error("添加用户失败");
        return ResponseResult.error();
    }

    @Override
    public ResponseResult update(User user) {
        return null;
    }

    @Override
    public ResponseResult Delete(Long uid) {
        int row = userMapper.deleteById(uid);
        if(row > 0){
            return ResponseResult.success();
        }
        log.error("删除用户失败");
        return ResponseResult.error();
    }

    @Override
    public ResponseResult selectById(@NotNull Long uid) {
        User user = userMapper.selectById(uid);
        return new ResponseResult("200","查询成功",user);
    }

    @Override
    public ResponseResult selectByUsernameAndPassword(@NotNull String username, @NotNull String password) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username",username)
                .eq("password",password));
        return new ResponseResult("200","查询成功",user);
    }
}
