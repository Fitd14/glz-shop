package com.glz.enums;

public enum CommodityStatusEnum {

    OK(0,"已发布"),
    CHECKING(1,"审核中");


    private int code;
    private String message;

    CommodityStatusEnum(int code,String message){
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
