package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

@Data
@TableName("t_commodity_type")
public class CommodityType {

    @TableId
    private Long id;

    /**
     * 类别名称
     */
    private String typeName;

    /**
     * 描述
     */
    private String details;

    /**
     * 是否是父节点
     */
    private boolean oneNode;

    /**
     * 节点名称
     */
    private CommodityType nodeName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否启用
     */
    private boolean startUsing;

    /**
     * 创建时间
     */
    private String created;

    /**
     * 更新时间
     */
    private String updated;



}
