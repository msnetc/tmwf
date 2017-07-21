package com.taimeitech.pass.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DevinInterceptor implements HandlerInterceptor {

    //在请求处理之前执行，该方法主要是用于准备资源数据的
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //身份验证
        //其他全局设置等

        return true;
    }

    //该方法将在Controller执行之后，返回视图之前执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //请求完成之后，也就是说在视图渲染之后进行调用，主要用于进行一些资源的释放
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
