package com.gdm.shop.controller;

import com.gdm.service.CollectTypeService;
import com.glz.model.ResponseResult;
import com.glz.pojo.CollectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class CollectTypeController {
    @Autowired
    CollectTypeService collectTypeService;
    @RequestMapping("/getType")
    public ResponseResult<List<CollectType>> getType(Long userId){
        return new ResponseResult<List<CollectType>>("200","success",collectTypeService.getList(userId));
    }
    @RequestMapping("/createType")
    public int create(Long uid,String typeName){
        return collectTypeService.createType(uid,typeName);
    }
    @DeleteMapping("/deleteType")
    public int delete(Long typeId,Long userId){
        return collectTypeService.delete(typeId,userId);
    }
}
