package com.sunny.token.response;

import lombok.Getter;

public class Response<T>{
    @Getter
    private String code;
    @Getter
    private String message;
    @Getter
    private T result;

    public Response(){}

    public Response(T result){
        this.result = result;
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMessage();
    }

    public Response(ResponseCode code){
        this.code = code.getCode();
        this.message = code.getMessage();
    }

    public T get(){
        return result;
    }

    public boolean success(){
        return ResponseCode.SUCCESS.equals(code);
    }
}