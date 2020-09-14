package com.smy.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glz.pojo.RoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {


    public Set<RoleMenu> findAll();

    public RoleMenu findByRoleId(@Param("roleId") String roleId);

}
