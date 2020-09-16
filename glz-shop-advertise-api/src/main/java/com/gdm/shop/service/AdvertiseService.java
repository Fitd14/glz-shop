package com.gdm.shop.service;

import com.glz.pojo.Advertise;

import java.util.List;

public interface AdvertiseService {
    int createAdvertise(Advertise advertise);
    Advertise getAdvertiseId(String aid);
    List<String> getList();
    List<Advertise> getAllList(Integer page,Integer rows);
    int delete(String aid);
}
