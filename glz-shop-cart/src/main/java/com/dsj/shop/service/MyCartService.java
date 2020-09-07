package com.dsj.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.Cart;

import java.util.List;
import java.util.Map;

public interface MyCartService {

    /**
     * 添加购物车
     * @param cart
     * @return
     */
    ResponseResult saveCart(Cart cart);

    /**
     * 获取用户购物车中所有的商品
     * @param userId
     * @return
     */
    ResponseResult listCart(Long userId);

    /**
     * 分页查询
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    ResponseResult pageCart(Long userId, Integer pageNo, Integer pageSize);

    /**
     * 删除用户购物车下某一商品
     * @param userId
     * @param commodityId
     * @return
     */
    ResponseResult deleteCart(Long userId, Long commodityId);

    /**
     * 清空购物车
     * @param userId
     * @return
     */
    ResponseResult clearCart(Long userId);

    /**
     * 修改商品数量
     * @param userId
     * @param commodityId
     * @param commodityCount
     * @return
     */
    ResponseResult updateCommodityCount(Long userId, Long commodityId, Integer commodityCount);

    /**
     * 批量删除
     * @param
     * @return
     */
    ResponseResult batchDelete(Long userId, Long[] commodityIds);
}