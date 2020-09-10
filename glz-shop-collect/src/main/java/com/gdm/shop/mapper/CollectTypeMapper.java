package com.gdm.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.glz.pojo.CollectType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Repository
public interface CollectTypeMapper extends BaseMapper<CollectType> {
    /**
     * 查询所得收藏夹
     * @return 返回查询收藏夹的集合
     */
    @Select("select * from t_collect_type where user_id=#{userId}")
    List<CollectType> getList(Long userId);

}
