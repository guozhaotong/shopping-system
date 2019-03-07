package com.guozhaotong.shoppingsystem.config;

import com.guozhaotong.shoppingsystem.intercepter.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 郭朝彤
 * @date 2019/3/7.
 */
//@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(authInterceptor);

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        //排除静态资源
        addInterceptor.excludePathPatterns("/static/**");
        addInterceptor.excludePathPatterns("/login.html");
        addInterceptor.excludePathPatterns("/login");
        addInterceptor.excludePathPatterns("/index.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        super.addResourceHandlers(registry);


    }


}
