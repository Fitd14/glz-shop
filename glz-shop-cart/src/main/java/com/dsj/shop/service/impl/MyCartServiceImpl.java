package com.dsj.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dsj.shop.mapper.CartMapper;
import com.dsj.shop.service.MyCartService;
import com.glz.model.ResponseResult;
import com.glz.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyCartServiceImpl implements MyCartService {

    @Autowired
    private CartMapper cartMapper;

    /**
     * 添加购物车
     * @param cart
     * @return
     */
    @Override
    public ResponseResult saveCart(Cart cart) {

        Cart one = cartMapper.selectOne(new QueryWrapper<Cart>().eq("user_id", cart.getUserId())
                .eq("commodity_id", cart.getCommodityId()));

        int row = 0;
        int update = 0;

        if(one != null){
            Integer count = one.getCommodityCount() + cart.getCommodityCount();
            update = cartMapper.update(one, new UpdateWrapper<Cart>()
                    .set("commodity_count", count)
                    .set("total_price", new BigDecimal(count).multiply(one.getPrice()))
                    .eq("cart_id", one.getCartId()));
        }else {
            row = cartMapper.insert(cart);
        }

        if(row >= 1 || update >= 1){
            return ResponseResult.success();
        }else {
            return ResponseResult.error();
        }
    }

    /**
     * 查询用户购物车列表
     * @param userId
     * @return
     */
    @Override
    public ResponseResult listCart(Long userId) {
        List<Cart> carts = cartMapper.selectList(new QueryWrapper<Cart>().eq("user_id", userId));
        return new ResponseResult("200", "success", carts);
    }

    /**
     * 分页查询
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public ResponseResult pageCart(Long userId, Integer pageNo, Integer pageSize) {
        IPage<Cart> page = new Page<>(pageNo, pageSize);
        IPage<Cart> cartIPage = cartMapper.selectPage(page, new QueryWrapper<Cart>().eq("user_id", userId));
        return new ResponseResult("200", "success", cartIPage);
    }

    /**
     * 删除购物车
     * @param userId
     * @param commodityId
     * @return
     */
    @Override
    public ResponseResult deleteCart(Long userId, Long commodityId) {

        Map<String, Object> deleteMap = new HashMap<>();
        deleteMap.put("user_id", userId);
        deleteMap.put("commodity_id", commodityId);

        int delete = cartMapper.deleteByMap(deleteMap);

        if(delete >= 1){
            return ResponseResult.success();
        }else {
            return ResponseResult.error();
        }
    }

    /**
     * 清空购物车
     * @param userId
     * @return
     */
    @Override
    public ResponseResult clearCart(Long userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);

        int i = cartMapper.deleteByMap(map);

        if(i >= 1){
            return ResponseResult.success();
        }else {
            return ResponseResult.error();
        }
    }

    /**
     * 修改商品数量
     * @param userId
     * @param commodityId
     * @param commodityCount
     * @return
     */
    @Override
    public ResponseResult updateCommodityCount(Long userId, Long commodityId, Integer commodityCount) {
        Cart one = cartMapper.selectOne(new QueryWrapper<Cart>().eq("user_id", userId)
                .eq("commodity_id", commodityId));

        int update = cartMapper.update(one, new UpdateWrapper<Cart>().set("commodity_count", commodityCount)
                .set("total_price", new BigDecimal(commodityCount).multiply(one.getPrice())));

        if(update >= 1){
            return ResponseResult.success();
        }else {
            return ResponseResult.error();
        }
    }

    /**
     * 批量删除
     * @param
     * @return
     */
    @Override
    public ResponseResult batchDelete(Long userId, Long[] commodityIds) {
        Map batchDeleteMap = new HashMap();
        batchDeleteMap.put("userId", userId);
        batchDeleteMap.put("commodityId", commodityIds);
        Integer row = cartMapper.batchDelete(batchDeleteMap);
        if(row >= 1){
            ResponseResult.success();
        }
        return ResponseResult.error();
    }
}
