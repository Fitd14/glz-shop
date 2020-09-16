package com.smy.shop.service;

import com.glz.model.ResponseResult;
import com.glz.pojo.CommodityCategory;

public interface CommodityCategoryService {
    ResponseResult add(CommodityCategory commodityCategory);

    ResponseResult update(CommodityCategory commodityCategory);
    /**
     * 通過父id更新children属性，
     * @return
     */
    ResponseResult updateByParentId(Long id,String name);

    ResponseResult del(Long id);

    ResponseResult selAll();

    /**
     * 通過父id查询，
     * @return所有的父类行
     */
    ResponseResult selByParentId(Long parentId);

    /**
     * 通過id查询，
     * @return
     */
    ResponseResult selById(Long id);

    /**
     * 通過name查询，
     * @return
     */
    ResponseResult selByName(String name);

    /**
     * 通过父类ID查询出该父类下所有子类
     * @param parentId
     * @return
     */
    ResponseResult querySubclass(Long parentId);

}
