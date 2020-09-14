package com.smy.shop.service;

import com.glz.model.ResponseResult;
import com.glz.model.RoleUserDTO;
import com.glz.pojo.Role;
import com.glz.pojo.RoleUser;

import java.util.List;
import java.util.Set;

public interface RoleUserService {

    ResponseResult save(RoleUser roleUser);

    ResponseResult delete(String roleId,String userId);

    Set<RoleUserDTO> selectAll();

    ResponseResult update(RoleUser roleUser);

    RoleUser selectByUserIdAndRoleId(RoleUser roleUser);

    RoleUser selectByUserId(String UserId);

}
