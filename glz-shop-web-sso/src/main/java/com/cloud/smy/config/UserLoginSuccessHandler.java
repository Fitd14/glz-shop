package com.cloud.smy.config;

import com.cloud.smy.util.JWTTokenUtil;
import com.glz.pojo.User;
import com.smy.shop.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Reference
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 组装JWT
        String username = authentication.getName();
        String token = JWTTokenUtil.createTokenByUsername(username);
        token = JWTConfig.tokenPrefix + token;

        String refreshToken = JWTTokenUtil.createRefreshTokenByUsername(username);

        User user = userService.selectByUsername(username);


        // 封装返回参数
        Map<String,Object> resultData = new HashMap<>();
        resultData.put("code","200");
        resultData.put("msg", "登录成功");
        resultData.put("token",token);
        // ResultUtil.responseJson(response,resultData);
    }
}
