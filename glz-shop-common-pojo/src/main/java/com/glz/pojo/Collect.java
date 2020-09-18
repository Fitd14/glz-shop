package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_collect")
public class Collect {
    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 商品Id
     */
    private String comId;
    /**
     * 商品名称
     */
    private String commodityName;
    /**
     * 商品类别
     */
    private String brand;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 收藏时商品价格
     */
    private BigDecimal price;
    /**
     * 备注
     */
    private String note;
    /**
     * 商品图片
     */
    private String photo;
    /**
     * 收藏夹类别Id
     */
    private String typeId;
    /**
     * 创建时间
     */
    private String createTime;
}
