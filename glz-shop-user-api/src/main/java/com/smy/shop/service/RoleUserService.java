package com.smy.shop.service;

import com.glz.pojo.Role;
import com.glz.pojo.RoleUser;

import java.util.List;

public interface RoleUserService {

    int save(Role role);

    int deleteById(long id);

    List<RoleUser> selectAll();

    int update(Role role);
}
