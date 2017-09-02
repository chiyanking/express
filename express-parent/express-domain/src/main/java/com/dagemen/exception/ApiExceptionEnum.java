package com.dagemen.exception;

/**
 * Created by kingdom on 2017/6/15.
 */
public enum ApiExceptionEnum implements BaseExceptionCode {

    USERNOTLOGIN("1000", "用户未登录"),
    USERRIGHTERROR("1001", "用户权限不足"),
    USER_LOGIN_ERROR("1002", "用户名或密码错误"),
    CREATE_EXP_MODEL_ERROR("2001","打印快递单失败"),
    NO_SELECTE_COMPANY("2002", "该快递点未选择快递公司"),

    ExpressStatusError("3001","快递单状态不对");

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
