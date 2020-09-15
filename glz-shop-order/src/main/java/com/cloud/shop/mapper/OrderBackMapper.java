package com.cloud.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glz.pojo.OrderBack;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderBackMapper extends BaseMapper<OrderBack> {
}
