package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_home_new_product")
public class NewProduct {
    @TableId
    /**
     * 广告区id
     */
    private String id;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 商品名
     */
    private String productName;
    /**
     * 状态
     */
    private Integer recommendStatus;
    /**
     * 排序
     */
    private Integer sort;
}
