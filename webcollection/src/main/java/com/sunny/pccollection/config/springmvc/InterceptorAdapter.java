package com.sunny.pccollection.config.springmvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class InterceptorAdapter implements WebMvcConfigurer {

    @Autowired
    MvcInterceptorAdapter mvcInterceptorAdapter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(mvcInterceptorAdapter)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathPatternsArray());
    }

    /**
     * 排除的路径表达式列表
     * @return
     */
    private List excludePathPatternsArray(){
        return Arrays.asList(
                "/swagger-resources/**"
                ,"/v2/api-docs/**"
                ,"/swagger-ui.html"
                ,"/webjars/**"
        );
    }
}
