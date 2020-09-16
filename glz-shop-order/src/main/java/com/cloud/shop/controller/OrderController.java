package com.cloud.shop.controller;

import com.cloud.smy.service.OrderService;
import com.glz.model.OrderDTO;
import com.glz.model.ResponseResult;
import com.glz.pojo.Cart;
import com.glz.pojo.Order;
import com.glz.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.annotation.WebInitParam;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    /**
     * 获得所有订单
     */
    @GetMapping("/all")
    public ResponseResult all() {
        return orderService.allOrder();
    }

    /**
     * 跨域测试
     */
    @GetMapping("/aaa")
    public ResponseResult aaa() {
        return ResponseResult.success();
    }

    /**
     * 通过一个购物车记录添加一条订单信息
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody OrderDTO orderDTO) {
        return orderService.addOrder(orderDTO);
    }

    /**
     * 通过订单号删除一条订单，并删除订单明细
     *
     * @Param orderNo 传入要删除的订单ID
     */
    @GetMapping("/del/{orderNo}")
    public ResponseResult delOrder(@PathVariable String orderNo) {
        return orderService.delOrder(orderNo);
    }


    /**
     * 通过用戶ID查询该用户的所有订单
     *
     * @Param userId 用户ID
     */
    @GetMapping("/{userId}")
    public ResponseResult listOrder(@PathVariable String userId) {
        return new ResponseResult("200", "success", orderService.listOrder(userId));
    }

    /**
     * 查询在某段时间之后的订单，没有实现
     *
     * @Param userId 用户ID
     * @Param createTime 创建时间
     */
    @GetMapping("/time/after")
    public ResponseResult timeOrder(@RequestParam("userId") String userId, @RequestParam("createTime") String createTime) {
        List<Order> orders = orderService.timeOrders(userId, createTime);
        return new ResponseResult("200", "success", orders);
    }

    /**
     * 更新订单状态
     *
     * @Param order 更新的order实体类
     */
    @PostMapping("/udp")
    public ResponseResult udp(Order order) {
        return orderService.updateOrder(order);
    }

    /**
     * 分页查询
     *
     * @Param userId 用户ID
     * @Param pageNO 当前页数
     * @Param pageSize 当前页展示的条数
     */
    @GetMapping("/page/list")
    public ResponseResult delOrder(@RequestParam("userId") String userId, @RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        return orderService.listOrderPage(userId, pageSize, pageNo);
    }

    /**
     * 根据ID集合删除订单及订单详情
     *
     * @Param ids 订单ID集合
     */
    @GetMapping("/del/ids")
    public ResponseResult delBatch(List<String> ids) {
        return orderService.delByIdlist(ids);
    }

    /**
     * 根据收货状态查询
     */
    @GetMapping("/status")
    public ResponseResult getByStatus(@RequestParam("userId") String userId, @RequestParam("status") int status) {
        return orderService.getByStatus(userId, status);
    }

    /**
     * 根据付款状态查询
     */
    @GetMapping("/pay/status")
    public ResponseResult getByPaytatus(@RequestParam("userId") String userId, @RequestParam("payStatus") int payStatus) {
        return orderService.getByPayStatus(userId, payStatus);
    }

}
