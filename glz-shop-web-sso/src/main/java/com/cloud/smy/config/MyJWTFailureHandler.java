package com.cloud.smy.config;

import com.alibaba.fastjson.JSON;
import com.cloud.smy.util.SecurityUtils;
import com.glz.constant.HttpStatus;
import com.glz.model.ResponseResult;
import com.smy.shop.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class MyJWTFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Reference
    UserService userService;

    @Autowired
    SecurityUtils securityUtils;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("[" + request.getParameter("username")  + "] => api登录失败 -> " + exception.getMessage());
        // securityUtils.cacheLoginErrorCount(request);

        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        PrintWriter writer = response.getWriter();
        Integer code = HttpStatus.UNAUTHORIZED;
        writer.write(JSON.toJSONString(new ResponseResult(code.toString(), exception.getMessage())));
        writer.flush();
        writer.close();
    }
}
