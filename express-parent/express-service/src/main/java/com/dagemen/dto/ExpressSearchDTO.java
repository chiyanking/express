package com.dagemen.dto;

import java.util.Date;

/**
 * Created by 丁芙蓉 on 2017/8/20.
 */
public class ExpressSearchDTO {

    private int page;
    private int pageSize;
    private String expCode;
    private Long pointId;
    private Date startDate;
    private Date endDate;
    private boolean isPrnt;
    private String senderName;
    private String companyName;

    public ExpressSearchDTO() {
    }

    public String getExpCode() {
        return expCode;
    }

    public void setExpCode(String expCode) {
        this.expCode = expCode;
    }

    public Long getPointId() {
        return pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isPrnt() {
        return isPrnt;
    }

    public void setPrnt(boolean prnt) {
        isPrnt = prnt;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
