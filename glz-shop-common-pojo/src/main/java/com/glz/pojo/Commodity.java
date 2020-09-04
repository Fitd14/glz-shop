package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sun.istack.internal.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
    private CommodityType commodityType;

    /**
     * 品牌方
     */
    private String brand;

    /**
     * 商品库存
     */
    private Inventory inventory;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 上下架状态
     */
    private Integer putawayStatus;

    /**
     * 图片
     */
    private UploadFile photo;

    /**
     * 商品价格
     */
    private int price;

    private String created;

    private String updated;
}
