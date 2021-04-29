package com.instructional.system.management.config;

import com.instructional.system.management.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {


    /**
     * 登录session key
     */
    public final static String SESSION_KEY = "user";


    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(loginInterceptor);
        // 拦截配置
        addInterceptor.excludePathPatterns("/login**", "/user/info**", "/menus**");
    }
}
