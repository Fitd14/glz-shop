package com.dsj.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glz.pojo.Cart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CartMapper extends BaseMapper<Cart> {

    @Delete("<script>"  +
            "delete from t_cart where user_id = #{userId} and commodity_id in " +
            "<foreach collection='commodityId' item='id' open='(' separator=',' close=')'> " +
            "#{id}" +
            "</foreach>" +
            "</script>")
    Integer batchDelete(Map batchDeleteMap);

    @Select("<script>"  +
            "select * from t_cart where user_id = #{userId} and commodity_id in " +
            "<foreach collection='commodityId' item='id' index='index' open='(' separator=',' close=')'> " +
            "#{id}" +
            "</foreach>" +
            "</script>")
    List<Cart> batchCart(Map map);
}
