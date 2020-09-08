package com.smy.shop.service;

import com.glz.pojo.Role;

import java.util.List;

public interface RoleService {

    int save(Role role);

    int deleteById(long id);

    List<Role> selectAll();

    int update(Role role);

}
