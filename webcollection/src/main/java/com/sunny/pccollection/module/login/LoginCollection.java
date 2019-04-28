package com.sunny.pccollection.module.login;

import com.sunny.pccollection.common.param.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "登录模块")
@RestController
@RequestMapping("login")
public class LoginCollection {

    @ApiOperation(value = "获取Token")
    @GetMapping("getToken")
    public String getToken(LoginUser loginUser){

        return "getToken";
    }

    @ApiOperation(value = "设置token")
    @PostMapping
    public String postToken(LoginUser loginUser){
        return null;
    }
}
