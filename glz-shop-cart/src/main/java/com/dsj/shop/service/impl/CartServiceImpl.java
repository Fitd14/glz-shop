package com.dsj.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dsj.shop.mapper.CartMapper;
import com.dsj.shop.service.CartService;
import com.glz.pojo.Cart;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对外公共接口实现
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public Cart getCart(String userId, String commodityId) {
        Cart cart = cartMapper.selectOne(new QueryWrapper<Cart>().eq("user_id", userId).eq("commodity_id", commodityId));
        return cart;
    }

    @Override
    public Integer deleteCart(String userId, String commodityId) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("commodity_id", commodityId);
        int i = cartMapper.deleteByMap(map);
        return i;
    }

    @Override
    public List<Cart> listCart(String userId) {
        List<Cart> carts = cartMapper.selectList(new QueryWrapper<Cart>().eq("user_id", userId));
        return carts;
    }

    @Override
    public Integer deleteCartAll(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        int i = cartMapper.deleteByMap(map);
        return i;
    }

    @Override
    public Integer batchDelete(String userId, String[] commodityIds) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("commodityId", commodityIds);
        Integer row = cartMapper.batchDelete(map);
        return row;
    }

    @Override
    public List<Cart> batchCart(String userId, String[] commodityIds) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("commodityId", commodityIds);
        List<Cart> carts = cartMapper.batchCart(map);
        return carts;
    }

    @Override
    public Cart getCartById(Long id) {
        return cartMapper.selectById(id);
    }

    @Override
    public int delCartById(Long id) {
        int i = cartMapper.deleteById(id);
        return i;
    }
}
