package com.smy.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.Role;

import java.util.List;

public interface RoleService {

    ResponseResult save(Role role);

    ResponseResult deleteById(String id);

    List<Role> selectAll();

    ResponseResult update(Role role);

    Role selectById(String id);
}
