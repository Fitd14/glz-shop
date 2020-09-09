package com.cloud.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glz.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    // List<Order> list(@Param("userId") Long userId, @Param("createTime") String createTime);

    List<Order> lists();

}
