package com.dsj.shop.handler;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;



/**
 * 实现mybatis-plus的自动插入时间和更新时间
 */
@Component
public class MyDateTimeHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime", DateUtil.now(), metaObject);
        this.setFieldValByName("updateTime", DateUtil.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", DateUtil.now(), metaObject);
    }
}
