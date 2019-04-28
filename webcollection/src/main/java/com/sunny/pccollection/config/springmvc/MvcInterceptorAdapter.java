package com.sunny.pccollection.config.springmvc;

import com.sunny.token.TokenService;
import com.sunny.token.module.UserInfoDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
public class MvcInterceptorAdapter extends HandlerInterceptorAdapter {

    @Resource(name = "localTokenService")
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (Objects.isNull(tokenService)) {
            throw new Exception("请在启动类中添加扫描com.sunny.token");
        }
        // TODO 这里传入request和 response 参数并不合理，需要把request和response做的操作移动到这边来
        UserInfoDto userInfoDto = tokenService.authToken(request, response);
        if (!Objects.isNull(userInfoDto)) {
            return false;
        }
        return super.preHandle(request, response, handler);
    }

}
