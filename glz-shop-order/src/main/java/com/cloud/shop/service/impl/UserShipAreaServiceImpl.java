package com.cloud.shop.service.impl;

import com.cloud.shop.mapper.UserShipAreaMapper;
import com.cloud.smy.service.UserShipAreaService;
import com.glz.model.ResponseResult;
import com.glz.pojo.UserShipArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserShipAreaServiceImpl implements UserShipAreaService {
    @Autowired
    UserShipAreaMapper userShipAreaMapper;

    /**
     * 根据用户ID查找地址
     */
    @Override
    public ResponseResult allShipArea(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        List<UserShipArea> userShipAreas = userShipAreaMapper.selectByMap(map);
        return new ResponseResult("200", "success", userShipAreas);
    }

    /**
     * 添加地址
     */
    @Override
    public ResponseResult addShipArea(UserShipArea userShipArea) {
        int i = userShipAreaMapper.insert(userShipArea);
        if (i != 0) {
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    /**
     * 根据id查找地址
     */
    @Override
    public ResponseResult getShipAreaById(String id) {
        UserShipArea userShipArea = userShipAreaMapper.selectById(id);
        return new ResponseResult("200", "success", userShipArea);
    }

    /**
     * 根据id删除地址
     */
    @Override
    public ResponseResult delShipAreaById(String id) {
        int i = userShipAreaMapper.deleteById(id);
        if (i != 0) {
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }
}
