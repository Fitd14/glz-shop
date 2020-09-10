package com.cloud.smy.config;

import com.alibaba.fastjson.JSON;
import com.cloud.smy.util.JWTTokenUtil;
import com.glz.constant.HttpStatus;
import com.glz.model.ResponseResult;
import com.glz.model.UserDTO;
import com.glz.pojo.User;
import com.smy.shop.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.http.entity.ContentType;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;


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

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setToken(token);
        userDTO.setRefreshToken(refreshToken);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        // response.setHeader("token", tokenStr);

        PrintWriter writer = response.getWriter();
        Integer code = HttpStatus.OK;
        writer.write(JSON.toJSONString(new ResponseResult(code.toString(),"登陆成功",userDTO)));
        writer.flush();
        writer.close();
    }
}
