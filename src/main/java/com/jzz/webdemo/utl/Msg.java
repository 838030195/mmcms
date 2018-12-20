package com.jzz.webdemo.utl;

public class Msg<T> {

    private int code;
    private String message;
    private T data;

    public Msg() {

    }

    public Msg(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public static Msg ok(int code, String msg, Object object) {
        return new Msg<>(code, msg, object);
    }

    public static Msg ok(String msg, Object object) {
        return ok(0, msg, object);
    }

    public static Msg err(String msg, Object object) {
        return new Msg<>(-1, msg, object);
    }

    public static Msg err(String msg) {
        return err(msg, null);
    }
}
