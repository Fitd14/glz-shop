package com.smy.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.glz.model.ResponseResult;
import com.glz.pojo.CommodityCategory;
import com.smy.shop.mapper.CommodityCategoryMapper;
import com.smy.shop.service.CommodityCategoryService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service
@Component
public class CommodityCategoryServiceImpl implements CommodityCategoryService {

    @Autowired
    CommodityCategoryMapper commodityCategoryMapper;
    @Override
    public ResponseResult add(CommodityCategory commodityCategory) {
        commodityCategory.setNavStatus(1);
        commodityCategory.setShowStatus(1);

        int insert = commodityCategoryMapper.insert(commodityCategory);
        if (insert > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult update(CommodityCategory commodityCategory) {
//        CommodityCategory category = commodityCategoryMapper.selectById(commodityCategory.getId());
        int update = commodityCategoryMapper.update(commodityCategory, new UpdateWrapper<CommodityCategory>().eq("id", commodityCategory.getId()));
        if (update > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult updateByParentId(Long id,String name) {
        CommodityCategory fathercategory = commodityCategoryMapper.selectById(id);
        CommodityCategory childrencategory = commodityCategoryMapper.selectOne(new QueryWrapper<CommodityCategory>().eq("name",name));
        int update = commodityCategoryMapper.update(fathercategory, new UpdateWrapper<CommodityCategory>().set("children", childrencategory.getId()).eq("id",id));
        if (update > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult del(Long id) {
        int delete = commodityCategoryMapper.deleteById(id);
        if (delete > 0){
            return ResponseResult.success();
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult selAll() {
        List<CommodityCategory> commodityCategories = commodityCategoryMapper.selectList(new QueryWrapper<CommodityCategory>());
        if (commodityCategories.size()>0){
            return new ResponseResult("200","success",commodityCategories);
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult selByParentId(Long parentId) {
        List<CommodityCategory> commodityCategories = commodityCategoryMapper.selectList(new QueryWrapper<CommodityCategory>().eq("parent_id",parentId));
        if (commodityCategories.size()>0){
            return new ResponseResult("200","success",commodityCategories);
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult selById(Long id) {
        CommodityCategory category = commodityCategoryMapper.selectById(id);
        if (category!=null){
            return new ResponseResult("200","success",category);
        }
        return ResponseResult.error();
    }

    @Override
    public ResponseResult selByName(String name) {
        CommodityCategory category = commodityCategoryMapper.selectOne(new QueryWrapper<CommodityCategory>().eq("name",name));
        if (category!=null){
            return new ResponseResult("200","success",category);
        }
        return ResponseResult.error();
    }

    /**
     * 通过父类ID查询该父类下的子类
     * @param parentId
     * @return
     */
    @Override
    public ResponseResult querySubclass(Long parentId) {
        List<CommodityCategory> parentIds = commodityCategoryMapper.selectList(new QueryWrapper<CommodityCategory>().eq("parent_id", parentId));
        return new ResponseResult("200", "success", parentIds);
    }


}
