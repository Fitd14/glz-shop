package com.gdm.shop.controller;

import com.gdm.shop.service.AdvertiseService;
import com.glz.model.ResponseResult;
import com.glz.pojo.Advertise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop/adver")
public class AdvertiseController {
    @Autowired
    AdvertiseService advertiseService;

    @RequestMapping("getId/{aid}")
    public ResponseResult<Advertise> getId(@PathVariable("aid") String aid) {
        return new ResponseResult<Advertise>("200", "success", advertiseService.getAdvertiseId(aid));
    }

    @RequestMapping("create")
    public int createAdvertise(Advertise advertise) {
        return advertiseService.createAdvertise(advertise);
    }

    @RequestMapping("/getShowList")
    public ResponseResult getShowList() {
        return new ResponseResult("200", "success", advertiseService.getList());
    }

    @RequestMapping("/getAllList")
    public ResponseResult getAllList(Integer page, Integer rows) {
        return new ResponseResult("200", "success", advertiseService.getAllList(page, rows));
    }

    @DeleteMapping("/delete/{aid}")
    public int delete(@PathVariable("aid") String aid) {
        return advertiseService.delete(aid);
    }
}
