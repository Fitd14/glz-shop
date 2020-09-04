package com.smy.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.User;

public interface UserService {

    ResponseResult insert(User user);

    ResponseResult update(User user);

    ResponseResult Delete(Long uid);

    ResponseResult selectById(Long uid);

    ResponseResult selectByUsernameAndPassword(String username,String password);

}
