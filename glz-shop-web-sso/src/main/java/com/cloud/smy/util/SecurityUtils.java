package com.cloud.smy.util;

import com.wf.captcha.SpecCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class SecurityUtils {

    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    /**
     * 记录尝试登录失败次数的key值(redis前缀)
     */
    private static final String LOGIN_ERROR_COUNT_USERNAME_KEY = "spring:security:loginErrorCount:username";
    private static final String LOGIN_ERROR_COUNT_IP_KEY = "spring:security:loginErrorCount:ip";
    /**
     * 缓存的验证码信息的key值(redis前缀)
     */
    private static final String CACHE_VALIDATACODE_KEY = "spring:security:validateCode";

    /**
     * 最大尝试登录失败次数(同一session、同一用户名、同一ip地址)
     */
    private static final int LOGIN_ERROR_MAX_COUNT_USERNAME = 5;
    private static final int LOGIN_ERROR_MAX_COUNT_IP = 50;

    /**
     * 根据用户名尝试登陆失败后验证码解除时限
     */
    private static final Duration USE_CODE_TIMEOUT = Duration.ofMinutes(30);

    /**
     * 验证码失效时限 分钟
     */
    private static final Duration CODE_INVALID_TIMEOUT = Duration.ofMinutes(5);

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Collection<? extends GrantedAuthority> getAllPermission() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities;
    }

    public static boolean hasPermission(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        boolean hasPermission = false;
        for (GrantedAuthority grantedAuthority : authorities) {
            String authority = grantedAuthority.getAuthority();
            if (authority.equals(permission)) {
                hasPermission = true;
            }
        }
        return hasPermission;
    }

    public static User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User)authentication.getPrincipal();
    }

    public static void logout() {
        SecurityContextHolder.clearContext();
    }

    //============================== 非静态方法 =================================

    /**
     * 根据session中登录错误次数标识判断是否进行验证码验证
     * @param request
     * @return
     */
    public boolean isUseValidateCode(HttpServletRequest request) {
        String username = request.getParameter("username");
        boolean usernameFlag = false;
        // 根据用户名判断尝试失败次数
        if (!StringUtils.isEmpty(username)) {
            String usernameRedisKey = generateKey(LOGIN_ERROR_COUNT_USERNAME_KEY, username);
            Integer usernameCount = (Integer)serializableRedisTemplate.opsForValue().get(usernameRedisKey);
            usernameFlag = usernameCount != null && usernameCount >= LOGIN_ERROR_MAX_COUNT_USERNAME;
        }
        // 根据ip判断尝试失败次数
        String ip = HttpKit.getIpAddr(request).replaceAll(":", "");
        boolean ipFlag = false;
        if (!"127.0.0.1".equals(ip) && !"00000001".equals(ip)) {
            String ipRedisKey = generateKey(LOGIN_ERROR_COUNT_IP_KEY, ip);
            Integer ipCount = (Integer)serializableRedisTemplate.opsForValue().get(ipRedisKey);
            System.out.println(ipCount);
            ipFlag = ipCount != null && ipCount >= LOGIN_ERROR_MAX_COUNT_IP;
        }
        return usernameFlag || ipFlag;
    }

    /**
     * 登录失败时，记录登录失败次数
     * @param request
     * @param
     */
    public void cacheLoginErrorCount(HttpServletRequest request) {
        // 根据用户名记录登录失败次数(redis的key为空则初始化次数，次数小于限值就加一，次数大于等于限值则续期)
        String usernameRedisKey = generateKey(LOGIN_ERROR_COUNT_USERNAME_KEY, request.getParameter("username"));
        Integer usernameCount = (Integer)serializableRedisTemplate.opsForValue().get(usernameRedisKey);
        if (usernameCount == null){
            serializableRedisTemplate.opsForValue().set(usernameRedisKey, 1, USE_CODE_TIMEOUT);
        } else if (usernameCount < LOGIN_ERROR_MAX_COUNT_USERNAME) {
            serializableRedisTemplate.opsForValue().set(usernameRedisKey, usernameCount + 1, USE_CODE_TIMEOUT);
        } else {
            serializableRedisTemplate.expire(usernameRedisKey, USE_CODE_TIMEOUT.getSeconds(), TimeUnit.SECONDS);
        }
        // 根据ip记录登录失败次数(redis的key为空则初始化次数，次数小于限值就加一，次数大于等于限值则续期)
        String ipRedisKey = generateKey(LOGIN_ERROR_COUNT_IP_KEY, HttpKit.getIpAddr(request).replaceAll(":", ""));
        Integer ipCount = (Integer)serializableRedisTemplate.opsForValue().get(ipRedisKey);
        if (ipCount == null){
            serializableRedisTemplate.opsForValue().set(ipRedisKey, 1, USE_CODE_TIMEOUT);
        } else if (ipCount < LOGIN_ERROR_MAX_COUNT_IP) {
            serializableRedisTemplate.opsForValue().set(ipRedisKey, ipCount + 1, USE_CODE_TIMEOUT);
        } else {
            serializableRedisTemplate.expire(ipRedisKey, USE_CODE_TIMEOUT.getSeconds(), TimeUnit.SECONDS);
        }
    }

    /**
     * 无状态生成验证码
     * @return
     */
    public Map<String, String> createOrFlushValidateCode(String captchaId) {
        String validateCodeKey = generateKey(CACHE_VALIDATACODE_KEY, captchaId);
        String code = (String)serializableRedisTemplate.opsForValue().get(validateCodeKey);
        if(code==null) {
            captchaId = UUID.randomUUID().toString();
            validateCodeKey = generateKey(CACHE_VALIDATACODE_KEY, captchaId);
        }
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        serializableRedisTemplate.opsForValue().set(validateCodeKey, specCaptcha.text(), CODE_INVALID_TIMEOUT);
        Map<String, String> map = new HashMap<>();
        map.put("captchaImg", specCaptcha.toBase64());
        map.put("captchaId", captchaId);
        return map;
    }

    /**
     * 无状态验证验证码结果
     * @return
     */
    public boolean isVerValidateCode(String captchaId, String captchaCode) {
        boolean flag = false;
        String validateCodeKey = generateKey(CACHE_VALIDATACODE_KEY, captchaId);
        String value = (String)serializableRedisTemplate.opsForValue().get(validateCodeKey);
        if(!StringUtils.isEmpty(captchaId) && !StringUtils.isEmpty(captchaCode) && captchaCode.equalsIgnoreCase(value)) {
            flag = true;
        }
        // 验证一次后即移除缓存，需更换验证码重新认证
        serializableRedisTemplate.delete(validateCodeKey);
        return flag;
    }

    /**
     * 生成key
     * @param prefix
     * @param suffix
     * @return
     */
    private String generateKey(String prefix, String suffix) {
        return prefix + ":" + suffix;
    }

}
