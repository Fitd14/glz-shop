package com.cloud.smy.config;

import com.alibaba.fastjson.JSON;
import com.cloud.smy.util.JWTTokenUtil;
import com.glz.model.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;

/**
 * 构造自定义注销成功处理器
 */
@Slf4j
@Component
public class MyJWTLogoutHandler implements LogoutSuccessHandler {

    @Autowired
    RedisTemplate<String, Serializable> redisTemplate;

    /**
     * 用户登出返回结果
     * 这里要让前端清除token
     * @param request
     * @param response
     * @param authentication
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = null;
        String tokenHeader = request.getHeader(JWTTokenUtil.TOKEN_HEADER);
        if (tokenHeader != null && tokenHeader.startsWith(JWTTokenUtil.TOKEN_PREFIX)) {
            String token = tokenHeader.replace(JWTTokenUtil.TOKEN_PREFIX, "");
            username = JWTTokenUtil.getUsernameFromToken(token);
        }

        // 清除用户信息缓存
        redisTemplate.delete("spring:security:userDetails::" + username);
        log.info("[" + username + "] => api注销成功");

        SecurityContextHolder.clearContext();
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType(ContentType.APPLICATION_JSON.getMimeType());
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(JSON.toJSONString(ResponseResult.success()));
        writer.flush();
        writer.close();
    }
}
