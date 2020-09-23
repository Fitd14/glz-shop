package com.smy.shop.controller;

import com.glz.model.ResponseResult;
import com.glz.pojo.Commodity;
import com.glz.pojo.Inventory;
import com.smy.shop.service.CommodityService;
import com.smy.shop.utils.FtpUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/commodity")
@Scope("prototype")
public class CommdityController {
    @Value("${FTP_HOST}")
    private String FTP_HOST;
    @Value("${FTP_POST}")
    private int FTP_POST;
    @Autowired
    CommodityService commodityService;
    @Value("${localtion}")
    String localtion;
    @Value("${httpPath}")
    String httpPath;
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

    @RequestMapping("/check")
    public ResponseResult updateStatus(String id,String uid,int status){
        return commodityService.updateStatusById(id, uid,status);
    }

    @RequestMapping("/sel")
    public ResponseResult sel(String commodityName, String commoditySubHead,Integer category, String brand,
                              String max, String min,String specificType, Long pageNo,
                              Long pageSize, Integer putawayStatus){
        System.out.println("category = " + category);
        return   commodityService.getByOther(commodityName, commoditySubHead,
             category,  brand,max,min,specificType,pageNo,pageSize,putawayStatus);
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
    public String upload(MultipartFile file) throws IOException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
            String path = sdf.format(new Date());
            System.out.println(file.getOriginalFilename());

            String newFileName
                    = UUID.randomUUID().toString().replaceAll("-", "")
                    + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            FtpUtil.uploadFile("192.168.115.63", 21, "anonymous", "", "/", path
                    , newFileName, file.getInputStream());
            String imgURL = "http://" + this.FTP_HOST +":63"+ path + newFileName;
            return imgURL;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/update")
    public ResponseResult update(@RequestBody Commodity commodity){
        return commodityService.update(commodity);
    }

    @RequestMapping("/category")
    public ResponseResult queryCategory(Integer category){
        return commodityService.queryCategory(category);
    }

    @RequestMapping("/categorycount")
    public ResponseResult getByCategoryCount(Integer category,int count){
        return commodityService.getByCategory(category, count);
    }

    @GetMapping("/selGroupId")
    public ResponseResult selGroupId(String[] id){
        System.out.println(id);
        return commodityService.selGroupId(id);
    }

    @GetMapping("/likeName")
    public ResponseResult selLikeName(String commodityName){
        return commodityService.selLikeName(commodityName);
    }
}
