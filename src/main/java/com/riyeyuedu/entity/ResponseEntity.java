package com.riyeyuedu.entity;

public class ResponseEntity {
    // http 状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    private Object data;

    public ResponseEntity(Object data) {
        this(200, "success", data);
    }

    public ResponseEntity(String msg) {
        this(200, msg, null);
    }

    public ResponseEntity(String msg, Object data) {
        this(200, msg, data);
    }

    public ResponseEntity(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
