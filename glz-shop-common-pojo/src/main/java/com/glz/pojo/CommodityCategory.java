package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_commodity_category")
public class CommodityCategory {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Long parentId;
    private int level;
    private int navStatus;
    private int showStatus;
    private int sort;
    private String keywords;
    /**
     * 下一接層類型
     */
    private Long children;
}
