package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("t_order_back")
public class OrderBack {
    @TableId(type = IdType.AUTO)
    Long id;
    /**
     * 订单ID
     */
    String orderNo;
    /**
     * 商品ID
     */
    String commodityId;
    /**
     * 描述
     */
    String memo;
    /**
     * 审核状态
     */
    int status;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT)
    String createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    String updateTime;

}
