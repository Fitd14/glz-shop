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
    private String id;

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
    private int  category;

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
     * 审核状态 1是审核中   0审核过    2审核没通过
     */

    private Integer status;

    /**
     * 上下架状态 1上架   0下架
     */
    private Integer  putawayStatus = 0;

    /**
     * 图片
     */
    private String  photo;

    /**
     * 商品价格
     */
    private double price;
    /**
     * 商品重量
     */
    private double weight;
    /**
     * 商品高度
     */
    private double height;
    /**
     * 商品长度
     */
    private double length;
    /**
     * 商品宽度
     */
    private double width;

    /**
     * 详细属性（尺寸、颜色）
     */
    private String specificType;

    /**
     * 商品详情
     */
    private String productDetail;

    private String createTime;

    private String updateTime;
}
