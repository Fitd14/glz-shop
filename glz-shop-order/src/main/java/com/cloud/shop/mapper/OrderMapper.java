package com.cloud.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glz.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    // List<Order> list(@Param("userId") Long userId, @Param("createTime") String createTime);

    List<Order> lists();

    List<Order> dyList(@Param("userName") String userName, @Param("createTime") String createTime, @Param("provinces") String provinces);

    List<Order> timeList(@Param("startTime") String startTime, @Param("endTime") String endTime);

    List<Order> countList();
}
