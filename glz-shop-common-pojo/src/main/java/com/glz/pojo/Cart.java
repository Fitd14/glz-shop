package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_cart")
public class Cart {

    /**
     * 购物车主键ID，自增
     */
    @TableId(type = IdType.AUTO)
    private Long cartId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long commodityId;

    /**
     * 购买价格
     */
    private BigDecimal price;

    /**
     * 购买数量
     */
    private Integer commodityCount;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    /**
     * 最后一次修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateTime;
}
