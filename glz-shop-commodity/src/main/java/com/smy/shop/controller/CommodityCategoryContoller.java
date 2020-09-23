package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.CommodityCategory;
import com.smy.shop.service.CommodityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/commodityCategory")
public class CommodityCategoryContoller {
    @Autowired
    CommodityCategoryService service;

    @RequestMapping("/add")
    public ResponseResult add(@RequestBody CommodityCategory commodityCategory){
        return service.add(commodityCategory);
    }

    @RequestMapping("/del/{id}")
    public ResponseResult del(@PathVariable Long id){
        return service.del(id);
    }
    @RequestMapping("/update")
    public ResponseResult update(@RequestBody CommodityCategory commodityCategory){
        return service.update(commodityCategory);
    }

    @RequestMapping("/updateByParentId/{id}/{name}")
    public ResponseResult updateByParentId(@PathVariable Long id,@PathVariable String name){
        return service.updateByParentId(id, name);
    }

    @RequestMapping("/selAll")
    public ResponseResult selAll(){
        return service.selAll();
    }

    @RequestMapping("/selByParentId/{parentId}")
    public ResponseResult selByParentId(@PathVariable Long parentId){
        return service.selByParentId(parentId);
    }

    @RequestMapping("/selByName")
    public ResponseResult selByName(String name){

        return service.selByName(name);
    }
    @RequestMapping("/selById")
    public ResponseResult selById(Long id){
        return service.selById(id);
    }

    /**
     * 通过父类ID查询该父类下的所有子类
     * @param parentId
     * @return
     */
    @RequestMapping("/subclass")
    public ResponseResult querySubclass(Long parentId){
        return service.querySubclass(parentId);
    }

}
