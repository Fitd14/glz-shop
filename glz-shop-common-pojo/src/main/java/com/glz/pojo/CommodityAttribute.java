package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_commodity_attribute")
public class CommodityAttribute {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String inputList;
    private int sort;
    private  int type;
}
