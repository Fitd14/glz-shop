package com.gdm.shop.controller;

import com.gdm.shop.service.NewProductService;
import com.glz.model.ResponseResult;
import com.glz.pojo.NewProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/shop/newProduct")
public class NewProductController {
    @Autowired
    NewProductService newProductService;
    @RequestMapping("/getList")
    public ResponseResult getList(){
        return new ResponseResult("200","success",newProductService.getList());
    }
    @RequestMapping("/addProduct")
    public int add(NewProduct newProduct){
        return newProductService.addProduct(newProduct);
    }
    @DeleteMapping("/delete/{pid}")
    public int delete(@PathVariable String pid){
        return newProductService.delete(pid);
    }
    @RequestMapping("/getId/{id}")
    public ResponseResult getId(@PathVariable String id){
        return  new ResponseResult("200","success",newProductService.getId(id));
    }

}
