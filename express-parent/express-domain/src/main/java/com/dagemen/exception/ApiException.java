package com.dagemen.exception;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import org.apache.commons.lang3.StringUtils;

public class ApiException extends RuntimeException {
    private static final long serialVersionUID = 2685287081293576410L;
    private String errCode;
    private String message;
    private Object[] args;
    private boolean runtime;

    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
        this.message = message;
    }

    public ApiException(String code, String message) {
        super(message);
        this.errCode = code;
        this.message = message;
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public ApiException(String errCode, String message, Throwable cause) {
        super(message, cause);
        this.errCode = errCode;
    }

    public ApiException(Throwable e) {
        super(e);
        this.errCode = e.getClass().getName();
        this.message = StringUtils.join(new String[]{this.errCode, " - ", e.getMessage()});
        this.runtime = true;
    }

    public ApiException(String code, String message, Object... args) {
        this.errCode = code;
        this.message = message;
        this.args = args;
        this.resolveMessage();
    }

    public ApiException(BaseExceptionCode baseExceptionCode, Object... args) {
        this.errCode = baseExceptionCode.getCode();
        this.message = baseExceptionCode.getMessage();
        this.args = args;
        this.resolveMessage();
    }

    public Throwable fillInStackTrace() {
        return this;
    }

    private void resolveMessage() {
        if (!StringUtils.isBlank(this.message) && this.args != null && this.args.length >= 1) {
            this.message = String.format(this.message, this.args);
        }
    }

    public String getErrCode() {
        return this.errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public boolean isRuntime() {
        return this.runtime;
    }

    public void setRuntime(boolean runtime) {
        this.runtime = runtime;
    }
}