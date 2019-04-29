package com.sunny.token;

import com.alibaba.fastjson.JSONObject;
import com.sunny.token.module.UserInfoDto;
import com.sunny.token.response.Response;
import com.sunny.token.response.ResponseCode;
import com.sunny.token.util.ThreadResourcesUtil;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public abstract class TokenService {

    public static String tokenKey = "TOKEN";

    /**
     * 获取用户信息
     * @param commonToken 公共token
     * @return 用户信息
     */
    public abstract Response<UserInfoDto> getUserInfoDto(String commonToken);

    /**
     * 获取公共token作为服务间数据传递的标志
     * @param clientToken 客户端token
     * @return 公共token
     */
    public abstract String getCommonToken(String clientToken);

    /**
     * 校验token
     * 如果返回null，则拦截器调用端应该予以返回false，因为在校验逻辑中输出了返回信息
     * 如果返回实体也可以从 ThreadResourcesUtil.getCurrentUser() 获取用户信息
     * @param request 请求
     * @param response 响应
     * @return 用户信息dto
     */
    public UserInfoDto authToken(HttpServletRequest request, HttpServletResponse response){
        Object token = request.getHeader(TokenService.tokenKey);
        if (Objects.isNull(token)) {
            response401(response, new Response(ResponseCode.TOKEN_IS_NULL));
            return null;
        }
        String commonToken = this.getCommonToken(String.valueOf(token));
        if (StringUtils.isEmpty(commonToken)) {
            response401(response, new Response(ResponseCode.NO_LOGIN));
        }
        Response<UserInfoDto> responseUserInfo = this.getUserInfoDto(String.valueOf(token));

        System.out.println(JSONObject.toJSONString(responseUserInfo));
        if (!responseUserInfo.success()) {
            response401(response, responseUserInfo);
            return null;
        }
        ThreadResourcesUtil.putCurrentUser(responseUserInfo.get());
        return ThreadResourcesUtil.getCurrentUser();
    }

    private boolean response401(HttpServletResponse httpResponse, Response errorResponse){
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        try {
            httpResponse.getOutputStream().write(JSONObject.toJSONString(errorResponse).getBytes());
        } catch (IOException e) {
            // 响应出现问题
            e.printStackTrace();
        }
        return false;
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
