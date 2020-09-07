package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

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
    Long userId;

    /**
     * 收货地址
     */
    String shipAddress;

    /**
     * 收货人姓名
     */
    String shipName;

    /**
     * 收货人电话
     */
    String shipMobile;

    /**
     * 收货状态
     */
    int status;

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
