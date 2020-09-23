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
     * 商品名
     */
    @TableField(exist = false)
    String commodityName;
    /**
     * 商品副标题
     */
    private String commoditySubHead;
    /**
     * 描述
     */
    String memo;
    /**
     * 审核状态
     */
    int status;
    /**
     * 图片描述
     */
    String img;
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
