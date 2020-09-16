package com.gdm.shop.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.gdm.shop.mapper.NewProductMapper;
import com.glz.pojo.NewProduct;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Service
@Component
public class NewProductServiceImpl implements NewProductService{
    @Autowired
    NewProductMapper newProductMapper;
    @Override
    public int addProduct(NewProduct newProduct) {
        if (newProduct.getId()!=null){
            return newProductMapper.updateById(newProduct);
        }else {
            return newProductMapper.insert(newProduct);
        }
    }

    @Override
    public int updateProduct(String id,Integer status) {
        NewProduct oldProduct = getId(id);
        oldProduct.setRecommendStatus(status);
        return newProductMapper.update(oldProduct, new UpdateWrapper<NewProduct>().set("recommend_status",oldProduct.getRecommendStatus()).eq("id",id));
    }

    @Override
    public NewProduct getId(String id) {
        return newProductMapper.selectById(id);
    }

    @Override
    public List<NewProduct> getList() {
        return newProductMapper.getListOrder();
    }

    @Override
    public int delete(String id) {
        return newProductMapper.deleteById(id);
    }
}
