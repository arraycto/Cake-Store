package com.cake.shop.coolutil;

import java.io.Serializable;

/**
 * 响应结果类型Json
 *
 * @param <T> 服务器向客户端响应的数据的类型
 */
public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer state;
    private String message;
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(Integer state, String message) {
        this.state = state;
        this.message = message;
    }

    public ResponseResult(Integer state) {
        this.state = state;
    }

    public ResponseResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
