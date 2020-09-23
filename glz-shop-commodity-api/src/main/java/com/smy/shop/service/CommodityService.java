package com.smy.shop.service;


import com.glz.model.ResponseResult;
import com.glz.pojo.Commodity;
import com.glz.pojo.CommodityAttribute;
import com.glz.pojo.CommodityCategory;
import com.glz.pojo.Inventory;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;


public interface CommodityService {

    ResponseResult save(Commodity commodity,
                         Inventory inventory);

    ResponseResult deleteById(String id);

    ResponseResult updateAwayStatusById(String id,Integer putawayStatus);

    ResponseResult updateStatusById(String id,String vertifyName,int status);

    ResponseResult getByOther(String commodityName,
                              String commoditySubHead,
                              Integer category, String brand,String specificType,
                    String max, String min , Long pageNo,Long pageSize
            ,Integer putawayStatus);

    ResponseResult getLimit(@Param("pageNo") Long pageNo,@Param("pageSize")Long pageSize,
                            @Param("putawayStatus")Integer putawayStatus);

    ResponseResult selectOne(String id);

    ResponseResult  selectAll();

    ResponseResult update(Commodity commodity);

    /**
     * 根据类别ID查询商品
     * @param category
     * @return
     */
    ResponseResult queryCategory(Integer category);

    /**
     * 根据类别ID查询商品 指定数量
     * @param category
     * @return
     */
    ResponseResult getByCategory(Integer category ,int count);

    /**
     * 多个商品id，查询id
     * @param
     * @return
     */
    ResponseResult selGroupId(String[] id);

    ResponseResult selLikeName(String commodityName);
}
