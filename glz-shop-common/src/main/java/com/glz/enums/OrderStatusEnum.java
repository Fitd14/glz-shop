package com.glz.enums;

public enum OrderStatusEnum {
    OK(0,"交易完成"),
    UNPAY(1,"待支付");

    int code;
    String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    OrderStatusEnum() {
    };

   OrderStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
