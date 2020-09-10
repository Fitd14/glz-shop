package com.cloud.shop.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description AJAX请求跨域，
 * 重写WebMvcConfigurerAdapter的addCorsMappings方法就可以获得基于spring的跨域支持
 * @Author
 * @Date 2020/4/8
 */
//@Configuration
//public class CrossOriginConfig extends WebMvcConfigurerAdapter {
//
//    static final String ORIGINS[] = new String[]{"GET", "POST", "PUT", "DELETE"};
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedMethods(ORIGINS)
//                .maxAge(3600);
//    }
//}