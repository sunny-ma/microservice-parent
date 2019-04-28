package com.sunny.token;

import com.sunny.token.module.UserInfoDto;
import com.sunny.token.response.Response;
import com.sunny.token.response.ResponseCode;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟本地token
 */
@Component("localTokenService")
public class LocalTokenServiceImpl extends TokenService {

    private static Map commonTokens = new ConcurrentHashMap();
    private static Map clientTokens = new ConcurrentHashMap();

    @Override
    public Response<UserInfoDto> getUserInfoDto(String token) {
        if (StringUtils.isEmpty(token)) {
            new Response(ResponseCode.TOKEN_IS_NULL);
        }
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUsername(token);
        return new Response(userInfoDto);
    }

    @Override
    public String getCommonToken(String clientToken) {
        return clientToken;
    }
}
