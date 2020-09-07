package com.dsj.shop.service;

import com.glz.model.ResponseResult;
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
    Cart getCart(Long userId, Long commodityId);

    /**
     * 删除购物车
     * @param userId 用户ID
     * @param commodityId 商品ID
     * @return
     */
    Integer deleteCart(Long userId, Long commodityId);

    /**
     * 获取购物车列表
     * @param userId
     * @return
     */
    List<Cart> listCart(Long userId);

    /**
     * 清空购物车
     * @param userId
     * @return
     */
    Integer deleteCartAll(Long userId);

    /**
     * 批量删除
     * @param userId
     * @param commodityIds
     * @return
     */
    Integer batchDelete(Long userId, Long[] commodityIds);

    /**
     * 批量查询
     * @param userId
     * @param commodityIds
     * @return
     */
    List<Cart> batchCart(Long userId, Long[] commodityIds);
}
