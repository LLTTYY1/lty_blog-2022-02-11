package com.LTY.enums;

/**
 * @author 刘泰源
 * @version 1.8
 */
public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200, "操作成功"),
    // 登录
    NEED_LOGIN(401, "需要登录后操作"),
    NO_OPERATOR_AUTH(403, "无权限操作"),
    SYSTEM_ERROR(500, "出现错误"),
    USERNAME_EXIST(501, "用户名已存在"),
    PHONENUMBER_EXIST(502, "手机号已存在"),
    EMAIL_EXIST(503,"邮箱已存在"),
    REQUIRE_USERNAME(504,"必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    CONTEXT_NOT_NULL(506, "内容不能为空"),
    FILE_NOT_EQULS(507,"文件的类型不匹配请重新上传"),
    USERNAME_NOT_NULL(508, "用户名不能为空"),
    EMAIL_NOT_NULL(509, "邮箱不能为空"),
    NICKNAME_NOT_NULL(510, "昵称不能为空"),
    NICKNAME_EXIST(511,"昵称存在"),
    PASSWORD_NOT_NULL(512,"密码不能为空");

    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage) {
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
