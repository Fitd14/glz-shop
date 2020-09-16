package com.gdm.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glz.pojo.NewProduct;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewProductMapper extends BaseMapper<NewProduct> {
    @Select("select * from t_home_new_product order by sort")
    List<NewProduct> getListOrder();
}
