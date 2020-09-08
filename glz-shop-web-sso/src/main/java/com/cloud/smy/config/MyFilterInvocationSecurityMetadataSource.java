package com.cloud.smy.config;

import com.cloud.smy.service.CustomUserDetailsService;
import com.glz.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则取得权限码返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation)object).getHttpRequest();
        AntPathRequestMatcher matcher;
        List<Permission> permissions = customUserDetailsService.allSysPermissions();
        Set<ConfigAttribute> configAttributeSet = new HashSet<>();
        ConfigAttribute configAttribute;
        for (Permission permission : permissions) {
            matcher = new AntPathRequestMatcher(permission.getUri());
            String thisMethod = request.getMethod();
            //String cfgMethed = permission.getMethed();
            //&& (StringUtils.isEmpty(cfgMethed) || "ALL".equals(cfgMethed) || thisMethod.equals(cfgMethed))
            if (matcher.matches(request) ) {
                configAttribute = new SecurityConfig(permission.getValue() + ":" + thisMethod);
                configAttributeSet.add(configAttribute);
            }
        }
        return configAttributeSet;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
