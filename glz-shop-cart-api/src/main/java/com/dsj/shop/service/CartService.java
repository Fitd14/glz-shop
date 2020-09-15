package com.dsj.shop.service;

import com.glz.pojo.Cart;

import java.util.List;

/**
 * cart对外暴露公共接口
 */
public interface CartService {

    /**
     * 获取购物车信息
     * @param userId 用户ID
     * @param commodityId 商品ID
     * @return
     */
    Cart getCart(String userId, String commodityId);

    /**
     * 删除购物车
     * @param userId 用户ID
     * @param commodityId 商品ID
     * @return
     */
    Integer deleteCart(String userId, String commodityId);

    /**
     * 获取购物车列表
     * @param userId
     * @return
     */
    List<Cart> listCart(String userId);

    /**
     * 清空购物车
     * @param userId
     * @return
     */
    Integer deleteCartAll(String userId);

    /**
     * 批量删除
     * @param userId
     * @param commodityIds
     * @return
     */
    Integer batchDelete(String userId, String[] commodityIds);

    /**
     * 批量查询
     * @param userId
     * @param commodityIds
     * @return
     */
    List<Cart> batchCart(String userId, String[] commodityIds);
}
