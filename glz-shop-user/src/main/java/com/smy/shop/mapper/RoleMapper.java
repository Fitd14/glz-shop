package com.smy.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glz.pojo.Role;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper extends BaseMapper<Role> {

//    @Results(id = "roleMap",value = {
//        @Result(column = "id",property ="id" ),
//        @Result(column = "code",property ="code" ),
//        @Result(column = "name",property ="name" ),
//        @Result(column = "create_time",property ="createTime" ),
//        @Result(column = "update_time",property ="updateTime" ),
//    })

//    @Select("select * from t_role where id = #{0}")
    public Role findById();
}
