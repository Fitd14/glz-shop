package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.Commodity;
import com.glz.pojo.Inventory;
import com.smy.shop.service.CommodityService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/commodity")
public class CommdityController {
    String gloabURL = "";
    @Autowired
    CommodityService commodityService;
    @Value("${localtion}")
    String localtion;
    @RequestMapping("/save")
    public ResponseResult save(@RequestBody Commodity commodity, Inventory inventory)  {
        System.out.println("commodity1111111 = " + commodity);
        return commodityService.save(commodity,  inventory);
    }

    @RequestMapping("/del/{id}")
    public ResponseResult del(@PathVariable("id") String id){
        System.out.println("id = " + id);
        return commodityService.deleteById(id);
    }
    @RequestMapping("/away")
    public ResponseResult updateAwayStatus( String id, Integer putawayStatus){
        ResponseResult responseResult = commodityService.updateAwayStatusById(id, putawayStatus);
        return responseResult;
    }

    @RequestMapping("/check/{id}")
    public ResponseResult updateStatus(@PathVariable("id") String id,Long uid,String detail,int status){
        return commodityService.updateStatusById(id, uid,detail,status);
    }

    @RequestMapping("/sel")
    public ResponseResult sel(String commodityName, String commoditySubHead, String brand, String max, String min,String specificType, Long pageNo, Long pageSize, Integer putawayStatus){
        return   commodityService.getByOther(commodityName, commoditySubHead, brand,max,min,specificType,pageNo,pageSize,putawayStatus);
    }

    @RequestMapping("/limit")
    public ResponseResult limit(Long pageNo,Long pageSize,Integer putawayStatus){
        return commodityService.getLimit(pageNo, pageSize,putawayStatus);
    }

    @RequestMapping("/selectOne/{id}")
    public ResponseResult selOne(@PathVariable String id){
        return commodityService.selectOne(id);
    }

    @RequestMapping("/selectAll")
    public ResponseResult selectAll(){
        return commodityService.selectAll();
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile[] files) throws IOException {
        String  savePath = localtion;
        gloabURL = "";
        File file1 = new File( savePath);
        if(localtion.isEmpty()){
            file1.mkdir();
        }
        for (MultipartFile file: files) {
            String id = UUID.randomUUID().toString().replace("-", "");
            String originalFilename = file.getOriginalFilename();
            String suffex = originalFilename.substring(originalFilename.lastIndexOf("."));
            String path= id+suffex;
            if ("".equals(gloabURL)){
                gloabURL = localtion+path;
            }else {
                gloabURL = gloabURL +"," + localtion+path;
            }
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(localtion + File.separator +path) );
        }
        System.out.println("gloabURL = " + gloabURL);
        return gloabURL;
    }
    @RequestMapping("/update")
    public ResponseResult update(@RequestBody Commodity commodity){
        return commodityService.update(commodity);
    }
}
