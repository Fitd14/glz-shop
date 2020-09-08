package com.glz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult<M> {

    private String code;

    private String message;

    private M data;

    public ResponseResult(String code,String message){
        this.code = code;
        this.message = message;
    }


    public static ResponseResult success(){
        return new ResponseResult("200","成功");
    }

    public static ResponseResult error(){
        return new ResponseResult("500","失败");
    }
}
