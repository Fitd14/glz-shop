package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.annotation.security.DenyAll;

@Data
@TableName("t_inventory")
public class Inventory {

    @TableId
    private String id;

    /**
     * 商品id
     */
    private Long commodityId;

    /**
     * 总数
     */
    private Integer totalCount;

    /**
     * 剩余数量
     */
    private Integer existingCount;

    /**
     * 已消耗数量
     */
    private Integer consumeCount;

    /**
     * 创建时间
     */
    private String created;

    /**
     * 更新时间
     */
    private String updated;

}
