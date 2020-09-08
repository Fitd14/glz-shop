package com.smy.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.User;

public interface UserService {

    /**
     * 添加用户
     * @param user
     * @return
     */
    ResponseResult insert(User user);

    /**
     * 跟新用户
     * @param user
     * @return
     */
    ResponseResult update(User user);

    /**
     * 删除用户
     * @param uid
     * @return
     */
    ResponseResult Delete(Long uid);

    /**
     * 通过id查询某个用户
     * @param uid
     * @return
     */
    ResponseResult selectById(Long uid);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    ResponseResult selectByUsernameAndPassword(String username,String password);

    /**
     * 通过username查询用户
     * @param username
     * @return
     */
    User selectByUsername(String username);
}
