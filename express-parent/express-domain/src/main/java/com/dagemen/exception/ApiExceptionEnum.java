package com.dagemen.exception;

/**
 * Created by kingdom on 2017/6/15.
 */
public enum ApiExceptionEnum implements BaseExceptionCode {

    USER_ERROR("60000", "用户模块异常"),
    USER_EXITS_ERROR("60101", "用户已存在"),
    USER_NOT_EXITS_ERROR("60102", "用户不存在"),
    USER_ENCRYPT_ERROR("60103", "加密失败"),
    USER_CODE_ERROR("60104", "工号不能重复"),
    PHONE_NOT_EXIST_ERROR("60105", "手机号不存在"),
    USER_LOGIN_ERROR("60106", "登录用户不存在"),
    USER_PASSWORD_ERROR("60107", "修改新密码不存在"),
    USER_OLD_PASSWORD_ERROR("60108", "原密码不正确"),
    USER_PASSWORD_NOT_SAME_ERROR("60109", "两次输入的密码不一致"),
    CANNOT_SET_DEFAULT_PASSWORD_ERROR("60110", "不能设置为默认密码"),
    PATH_CANNOT_NULL_ERROR("60111", "路径不能为空"),
    AUTO_BRAND_ERROR("60201", "车辆品牌已存在"),
    AUTO_MODEL_MAIN_ERROR("60202", "车辆大车型已存在");

    private String errCode;
    private String message;

    ApiExceptionEnum(String errCode, String message) {
        this.errCode = errCode;
        this.message = message;
    }

    public ApiException buildException() {
        return new ApiException(this);
    }

    public String getCode() {
        return errCode;
    }

    public String getMessage() {
        return message;
    }
}
