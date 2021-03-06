package com.cloud.smy.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.UserShipArea;

public interface UserShipAreaService {
    /**
     * 查找用户的所有地址
     */
    ResponseResult allShipArea(String userId);

    /**
     * 添加新地址
     */
    ResponseResult addShipArea(UserShipArea userShipArea);

    /***
     * 通過id查找地址
     * */
    ResponseResult getShipAreaById(Long id);

    /**
     * 通过ID删除地址
     */
    ResponseResult delShipAreaById(Long id);

    /**
     * 更新收货地址
     */
    ResponseResult udpShipArea(UserShipArea userShipArea);
}
