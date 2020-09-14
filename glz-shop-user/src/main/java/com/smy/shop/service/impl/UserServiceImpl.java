package com.smy.shop.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.glz.enums.ResultEnum;
import com.glz.model.ResponseResult;
import com.glz.pojo.User;
import com.smy.shop.mapper.UserMapper;
import com.smy.shop.service.UserService;
import com.smy.shop.util.BCryptUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public ResponseResult insert(User user) {
        User existUser = selectByUsername(user.getUsername());
        if(ObjectUtil.isNotEmpty(existUser)){
            return new ResponseResult(ResultEnum.PARAME_ERROR.getCode(),"用户已存在");
        }
        user.setId(IdUtil.simpleUUID());
        user.setCreated(DateUtil.now());
        String encodePassword = BCryptUtils.encodePassword(user.getPassword());
        user.setPassword(encodePassword);
        int row = userMapper.insert(user);
        if(row > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult update(User user) {
        User oldUser = userMapper.selectById(user.getId());
        oldUser.setUpdated(DateUtil.now());
        oldUser.setEmail(user.getEmail());
        oldUser.setNickname(user.getNickname());
        oldUser.setStatus(user.getStatus());
        oldUser.setPhone(user.getPhone());
        oldUser.setIcon(user.getIcon());
        int row = userMapper.updateById(oldUser);
        if(row > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult delete(Long uid) {
        int row = userMapper.deleteById(uid);
        if(row > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult selectById(Long uid) {
        User user = userMapper.selectById(uid);
        return new ResponseResult("200","查询成功",user);
    }

    @Override
    public User selectByUsername(String username) {
        return userMapper.selectOne(new QueryWrapper<User>()
                .eq("username",username));
    }

    @Override
    public ResponseResult updatePasswordByUsername(String username,String password) {
        User oldUser = selectByUsername(username);
        oldUser.setPassword(BCryptUtils.encodePassword(password));
        int row = userMapper.update(oldUser,new UpdateWrapper<User>().set("password",oldUser.getPassword())
                .eq("username",oldUser.getUsername()));
        if(row > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult<List<User>> selectAll() {
        List<User> users = userMapper.selectList(new QueryWrapper<User>());
        return new ResponseResult(ResultEnum.OK.getCode(),ResultEnum.OK.getValue(),users);
    }


}
