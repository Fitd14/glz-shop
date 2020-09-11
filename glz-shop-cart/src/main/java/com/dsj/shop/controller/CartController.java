package com.dsj.shop.controller;

import com.dsj.shop.service.MyCartService;
import com.glz.model.ResponseResult;
import com.glz.pojo.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private MyCartService myCartService;

    /**
     * 添加购物车
     * @param cart
     * @return
     */
    @PostMapping("/add")
    public ResponseResult addCart(Cart cart){
        return myCartService.saveCart(cart);
    }

    /**
     * 查询购物车列表
     * @param userId
     * @return
     */
    @GetMapping("/list/{userId}")
    public ResponseResult listCart(@PathVariable Long userId){
        return myCartService.listCart(userId);
    }

    /**
     * 分页查询
     * @param userId 用户ID
     * @param pageNo 当前页
     * @param pageSize 每页个数
     * @return
     */
    @GetMapping("/list/page")
    public ResponseResult pageCart(@RequestParam("userId") Long userId, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        return myCartService.pageCart(userId, pageNo, pageSize);
    }

    /**
     * 修改商品数量
     * @param userId
     * @param commodityId
     * @param commodityCount
     * @return
     */
    @PutMapping("/update/count")
    public ResponseResult updateCount(@RequestParam("userId") Long userId, @RequestParam("commodityId") String commodityId, @RequestParam("commodityCount") Integer commodityCount){
        return myCartService.updateCommodityCount(userId, commodityId, commodityCount);
    }

    /**
     * 删除购物车
     * @param userId
     * @param commodityId
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseResult deleteCart(@RequestParam("userId") Long userId, @RequestParam("commodityId") String commodityId){
        return myCartService.deleteCart(userId, commodityId);
    }

    /**
     * 批量删除
     * @return
     */
    @DeleteMapping("/delete/batch")
    public ResponseResult lists(@RequestParam("userId") Long userId, @RequestParam("commodityIds") String[] commodityIds){
        return myCartService.batchDelete(userId, commodityIds);
    }

    /**
     * 清空购物车
     * @param userId
     * @return
     */
    @DeleteMapping("/deleteAll")
    public ResponseResult deleteAllCart(@RequestParam("userId") Long userId){
        return myCartService.clearCart(userId);
    }
}
