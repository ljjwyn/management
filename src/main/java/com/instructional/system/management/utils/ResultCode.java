package com.instructional.system.management.utils;

/**
 * @author ljjwyn
 */

public enum ResultCode {
    /*成功码*/
    SUCCESS(200,"成功"),
    TOKEN_INVALID(500,"登录信息失效"),
    /*参数异常*/
    PARAM_INVALID(501,"参数无效"),
    PARAM_BLANK(502,"参数为空"),
    /*数据库异常*/
    SQL_SELECT_BLANK(503,"查询结果为空"),
    /*服务异常*/
    SERVER_ERROR(504,"服务异常"),
    /*登录异常*/
    LOGIN_ACCOUNT_BLANK(505,"账号或者密码为空"),
    LOGIN_ACCOUNT_INVALID(506,"账号或者密码错误"),
    /*权限不足*/
    AUTHORITY_ERROR(507,"用户权限不足");
    private Integer code;
    private String message;

    ResultCode(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer code(){
        return this.code;
    }

    public String message(){
        return this.message;
    }

}
