package com.ebei.message.utlis;

/**
 * 
 * @Description : HTTP请求返回结果
 * @time 创建时间 : 2018年6月4日
 * @author : FanHua
 * @Copyright (c) 2018 一碑科技
 * @version
 */
public class HttpRequestResult {

    private int statusCode;

    private String Result;

    private String message;

    public HttpRequestResult() {
    }

    public HttpRequestResult(int statusCode, String result, String message) {
        this.statusCode = statusCode;
        Result = result;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
