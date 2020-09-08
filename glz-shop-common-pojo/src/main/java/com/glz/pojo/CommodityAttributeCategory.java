package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("commodity_attribute_category")
public class CommodityAttributeCategory {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private int attributeCount;

    private int paramCount;
}
