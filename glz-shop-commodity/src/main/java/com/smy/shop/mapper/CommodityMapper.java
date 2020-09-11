package com.smy.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glz.pojo.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {

    List<Commodity> getByOther(@Param("commodityName") String commodityName, @Param("commoditySubHead") String commoditySubHead,
                               @Param("brand") String brand,@Param("max") String max, @Param("min") String min
            ,@Param("specificType") String specificType,@Param("pageNo") Long pageNo,@Param("pageSize")Long pageSize,
                               @Param("putawayStatus")Integer putawayStatus);

    List<Commodity> selectAll();

    List<Commodity> getLimit(@Param("pageNo") Long pageNo,@Param("pageSize")Long pageSize,@Param("putawayStatus")Integer putawayStatus);
}
