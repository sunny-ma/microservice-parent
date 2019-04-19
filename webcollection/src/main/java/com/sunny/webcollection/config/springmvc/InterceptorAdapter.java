package com.sunny.webcollection.config.springmvc;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class InterceptorAdapter implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MvcAdapter())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathPatternsArray());
    }

    private List excludePathPatternsArray(){
        return Arrays.asList(
                "/swagger-resources/**"
                ,"/v2/api-docs/**"
                ,"/swagger-ui.html"
                ,"/webjars/**"
        );
    }
}
