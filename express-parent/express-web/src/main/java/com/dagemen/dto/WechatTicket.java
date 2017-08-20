package com.dagemen.dto;

import java.util.Date;

public class WechatTicket {
    private String ticket;
    private int expires_in;
    private Date expireDate;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
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
