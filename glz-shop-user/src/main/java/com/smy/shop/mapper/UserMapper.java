package com.smy.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glz.pojo.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserMapper extends BaseMapper<User> {

//    @Results(id = "userMap",value = {
//        @Result(column ="id" ,property ="id"),
//        @Result(column ="username" ,property ="username"),
//        @Result(column ="phone" ,property ="phone"),
//        @Result(column ="email" ,property ="email"),
//        @Result(column ="password" ,property ="password"),
//        @Result(column ="icon" ,property ="icon"),
//        @Result(column ="nickname" ,property ="nickname"),
//        @Result(column ="status" ,property ="status"),
//        @Result(column ="created" ,property ="created"),
//        @Result(column ="updated" ,property ="updated"),
//    })
//
//    @Select("select * from t_user where id = #{0}")
    public User findById();

}
