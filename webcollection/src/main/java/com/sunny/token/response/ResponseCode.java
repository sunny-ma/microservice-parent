package com.sunny.token.response;

import lombok.Getter;

/**
 * 响应CODE
 */
public enum ResponseCode{
    SUCCESS("0000", "成功"),
    ERROR("9999","异常"),
    TOKEN_IS_NULL("4001", "TOKEN 为空"),
    NO_LOGIN("4002", "您还没有登录"),
    ;

    ResponseCode(String code, String message){
        this.code = code;
        this.message = message;
    }
    @Getter
    private String code;
    @Getter
    private String message;

    /**
     * 判定状态是否是成功
     * @return
     */
    public boolean equals(String code){
        return this.code.equals(code);
    }

}