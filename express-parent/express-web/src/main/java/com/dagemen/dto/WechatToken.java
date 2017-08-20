package com.dagemen.dto;

import java.util.Date;

public class WechatToken {

    /**
     * token
     */
    private String access_token;

    /**
     * 失效时间长度
     */
    private int expires_in;

    private Date expireDate;

    public WechatToken() {
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }


    public boolean isNotExpired() {
        return expireDate != null && expireDate.after(new Date());
    }
}
