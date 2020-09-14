package com.cloud.smy.config;

import com.alibaba.fastjson.JSON;
import com.glz.constant.HttpStatus;
import com.glz.enums.ResultEnum;
import com.glz.model.ResponseResult;
import org.apache.http.entity.ContentType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Component
public class MyAuthenticationEntryPointHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException authException) throws IOException, ServletException {

        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        PrintWriter writer = response.getWriter();
        Integer code = HttpStatus.UNAUTHORIZED;
        writer.write(JSON.toJSONString(new ResponseResult(ResultEnum.NO_LOGIN.getCode(),ResultEnum.NO_LOGIN.getValue())));
        writer.flush();
        writer.close();
    }
}
