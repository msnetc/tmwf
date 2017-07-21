package com.taimeitech.pass.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by devin on 2017/5/17.
 */
@Configuration
public class InterceptorConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则, excludePathPatterns 用户排除拦截
        registry.addInterceptor(new DevinInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
