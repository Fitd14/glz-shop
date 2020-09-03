package com.glz.enums;

public enum UserStatusEnum {

    OK(0,"正常"),
    DISABLE(1,"禁用"),
    DELETE(2,"删除");

    private int code;
    private String message;

    UserStatusEnum(int code,String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
