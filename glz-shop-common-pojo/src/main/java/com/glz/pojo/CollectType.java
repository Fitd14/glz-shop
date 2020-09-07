package com.glz.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_collect_type")
public class CollectType {
    /**
     * 收藏夹id
     */
    @TableId
    private Long id;
    /**
     * 收藏夹名
     */
    private String typeName;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 创建时间
     */
    private String createTime;
}
