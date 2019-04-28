package com.sunny.pccollection.common.enumeration;

import org.springframework.http.HttpStatus;

public enum HttpResultEnum {

    SIGN_ERROR(HttpStatus.UNAUTHORIZED, "验签失败"),
    NO_SIGN_ERROR(HttpStatus.UNAUTHORIZED, "签名为空"),
    ;

    private HttpStatus status;

    private String message;

    HttpResultEnum(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }



}
