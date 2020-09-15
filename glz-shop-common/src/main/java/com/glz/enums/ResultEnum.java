package com.glz.enums;


public enum  ResultEnum {

    OK("200","成功"),

    UNKONW_ERROR("-1", "未知错误"),

    PASSWORD_ERROR("105", "密码错误"),

    NO_LOGIN("401", "未登录"),

    SERVER_ERROR("500", "服务异常"),

    PARAME_ERROR("400", "入参异常");

    private String code;
    private String value;

    ResultEnum(String code,String value){
        this.code = code;
        this.value =value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
