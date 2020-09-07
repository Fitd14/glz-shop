package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_commodity")
public class Commodity {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 商品名称
     */
    private String commodityName;

    /**
     * 商品副标题
     */
    private String commoditySubHead;

    /**
     * 商品类别
     */
    private int  commodityType;

    /**
     * 品牌方
     */
    private String brand;

    /**
     * 商品库存
     */
    private int inventory;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 审核状态 1是审核中   0审核过
     */

    private Integer status;

    /**
     * 上下架状态 0上架   1下架
     */
    private Integer putawayStatus;

    /**
     * 图片
     */
    private int  photo;

    /**
     * 商品价格
     */
    private double price;

    private String createTime;

    private String updateTime;
}
