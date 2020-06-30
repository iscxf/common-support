package com.iscxf.common.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenxf
 * Created on 2020/2/5
 */
@Data
public class Result<T> implements Serializable {
    private int errorCode;
    private String errorMessage;
    private T data;

    public Result() {
        this.errorCode = 0;
        this.errorMessage = "ok";
    }

    public Result(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Result(T data) {
        this.errorCode = 0;
        this.errorMessage = "success";
        this.data = data;
    }

    public Result(int errorCode, String errorMessage, T data) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public static <R> Result<R> newError(Integer errorCode, String errorMessage) {
        return new Result<>(errorCode, errorMessage);
    }
    public static <R> Result<R> newError(Integer errorCode, String errorMessage, R data) {
        return new Result<>(errorCode, errorMessage, data);
    }

    public static <R> Result<R> newSuccess() {
        return new Result<>();
    }

    public static <R> Result<R> newSuccess(R data) {
        return new Result<>(data);
    }

    public boolean isSuccess() {
        return errorCode == 0;
    }
}
