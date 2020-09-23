package com.smy.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glz.pojo.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Mapper
public interface CommodityMapper extends BaseMapper<Commodity> {

    List<Commodity> selLikeName(@Param("commodityName") String commodityName);
    List<Commodity> getByOther(@Param("commodityName") String commodityName, @Param("commoditySubHead") String commoditySubHead,
                              @Param("category") Integer category,@Param("brand") String brand, @Param("max") String max, @Param("min") String min
            , @Param("specificType") String specificType, @Param("pageNo") Long pageNo, @Param("pageSize")Long pageSize,
                               @Param("putawayStatus")Integer putawayStatus);

    List<Commodity> selectAll();

    List<Commodity> getLimit(@Param("pageNo") Long pageNo,@Param("pageSize")Long pageSize,@Param("putawayStatus")Integer putawayStatus);

    List<Commodity> getByCategoryCount(@Param("category") Integer category,@Param("count") int count);

    @Select("<script>"  +
            "select * from t_commodity where  id in " +
            "<foreach collection='id' item='id' index='index' open='(' separator=',' close=')'> " +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<Commodity> selByGroupId(String[] id);
}
