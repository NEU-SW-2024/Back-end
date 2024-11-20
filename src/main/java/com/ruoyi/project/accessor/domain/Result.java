package com.ruoyi.project.accessor.domain;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String msg;
    private Object data;
    public Result(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data=data;
    }
    public static Result success(){
        return new Result(1,"成功响应",null);
    }
    public static Result success(Object data){
        return new Result(1,"成功响应",data);
    }
    public static Result error(String msg){
        return new Result(0,msg,null);
    }
}
