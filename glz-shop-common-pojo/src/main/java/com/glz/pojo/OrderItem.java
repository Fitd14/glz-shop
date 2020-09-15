package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("t_order_item")
public class OrderItem {

    /**
     * 主鍵
     */
    @TableId(type = IdType.AUTO)
    Long id;

    /**
     * 订单ID
     */
    String orderNo;

    /**
     * 用户ID；
     */
    String userId;

    /**
     * 商品ID
     * */
    String commodityId;
    /**
     * 数量
     * */
    int number;

    /**
     * 价钱
     * */
    BigDecimal price;

    /**
     * 备注
     */
    String memo;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    String createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    String updateTime;

    /**
     * 所属店铺
     */
    int storeId;


}
