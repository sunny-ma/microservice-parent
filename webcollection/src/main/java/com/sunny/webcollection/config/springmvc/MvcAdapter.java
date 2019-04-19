package com.sunny.webcollection.config.springmvc;

import com.alibaba.fastjson.JSON;
import com.sun.net.httpserver.Headers;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class MvcAdapter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURL());
        String sign = request.getParameter("sign");
        if (Strings.isEmpty(sign)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getOutputStream().write("".getBytes());
            return false;
        }
        return super.preHandle(request, response, handler);
    }


    private Object getParam(HttpServletRequest request, String key){
        Object result;
        result = request.getParameter(key);
        if (StringUtils.isEmpty(request)) {
            result = request.getAttribute(key);
        }
        return result;
    }

}
