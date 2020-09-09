package com.smy.shop.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

public class BCryptUtils {

    @Autowired
    private static BCryptPasswordEncoder bCryptPasswordEncoder;

    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

}
