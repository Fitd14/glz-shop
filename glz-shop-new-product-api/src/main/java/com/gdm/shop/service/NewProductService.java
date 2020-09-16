package com.gdm.shop.service;

import com.glz.pojo.NewProduct;

import java.util.List;

public interface NewProductService {
    int addProduct(NewProduct newProduct);
    int updateProduct(String id,Integer status);
    NewProduct getId(String id);
    List<NewProduct> getList();
    int delete(String id);
}
