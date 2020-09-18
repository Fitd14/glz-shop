package com.gdm.shop.controller;

import com.gdm.service.CollectService;
import com.glz.model.ResponseResult;
import com.glz.pojo.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/shop/collect")
public class CollectController {
    @Autowired
    CollectService collectService;
    @RequestMapping("/getClollect")
    public ResponseResult<Collect> getCollectId(Long cid){
        return new ResponseResult<Collect>("200","success",collectService.getId(cid));
    }
    @RequestMapping("/delete/{cid}")
    public int deleteId(@PathVariable String cid){
        return collectService.delete(cid);
    }
    @RequestMapping("/create/{cid}/{uid}")
    public int createCollect(@PathVariable("cid") String cid,@PathVariable("uid") String uid){
        return collectService.create(cid,uid);
    }
    @RequestMapping("/getListType")
    public ResponseResult<List<Collect>> getCollectListId(Long typeId,Long userId,int page){
        return new ResponseResult<List<Collect>>("200","success",collectService.getListIdPage(typeId,userId,page-1));
    }
    @RequestMapping("/getList")
    public ResponseResult<List<Collect>> getCollectList(String userId,int page){
        return new ResponseResult<List<Collect>>("200","success",collectService.getList(userId,page-1));
    }
    @RequestMapping("/deletes")
    public String deletes(List<Long> ids){
        List<Long> idss = new ArrayList<>();
        collectService.deletes(ids);
        return "success";
    }
    @RequestMapping("/getListUid/{uid}")
    public ResponseResult getCollectUid(@PathVariable("uid") String uid){
        return new ResponseResult("200","success",collectService.getListUid(uid));
    }
}
