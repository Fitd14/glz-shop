package com.smy.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glz.model.RoleUserDTO;
import com.glz.pojo.RoleUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleUserMapper extends BaseMapper<RoleUser> {

//    @Results(id = "roleUserMap",value = {
//            @Result(column ="user_id",property ="userId",one = @One(select = "com.smy.shop.mapper.UserMapper.findById")),
//            @Result(column ="role_id",property ="roleId",one = @One(select = "com.smy.shop.mapper.RoleMapper.findById"))
//    })
//
//    @Select("select * from t_role_user")
//    @ResultMap("roleUserMap")
    public Set<RoleUserDTO> findAll();

}
