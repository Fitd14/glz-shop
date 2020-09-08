package com.gdm.shop.controller;

import com.gdm.service.CollectService;
import com.glz.model.ResponseResult;
import com.glz.pojo.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class CollectController {
    @Autowired
    CollectService collectService;
    @RequestMapping("/getClollect")
    public ResponseResult<Collect> getCollectId(Long cid){
        return new ResponseResult<Collect>("200","success",collectService.getId(cid));
    }
    @DeleteMapping("/delete")
    public int deleteId(Long cid){
        return collectService.delete(cid);
    }
    @RequestMapping("/create")
    public int createCollect(Long typeId,Long userId){
        return collectService.create(typeId,userId);
    }
    @RequestMapping("/getListType")
    public ResponseResult<List<Collect>> getCollectListId(Long typeId,Long userId,int page){
        return new ResponseResult<List<Collect>>("200","success",collectService.getListIdPage(typeId,userId,page-1));
    }
    @RequestMapping("/getList")
    public ResponseResult<List<Collect>> getCollectList(Long userId,int page){
        return new ResponseResult<List<Collect>>("200","success",collectService.getList(userId,page-1));
    }
    @DeleteMapping("/deletes")
    public String deletes(List<Long> ids){
        List<Long> idss = new ArrayList<>();
        idss.add(1302128894660354050L);
        idss.add(1302779453499269121L);
        collectService.deletes(ids);
        return "success";
    }
}
