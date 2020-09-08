package com.smy.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.Role;
import com.glz.pojo.RoleUser;

import java.util.List;

public interface RoleUserService {

    ResponseResult save(RoleUser roleUser);

    ResponseResult deleteById(long id);

    List<RoleUser> selectAll();

    ResponseResult update(RoleUser roleUser);

    RoleUser selectByUserId(long uid);
}
