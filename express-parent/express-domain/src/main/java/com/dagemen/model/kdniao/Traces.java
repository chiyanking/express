package com.dagemen.model.kdniao;

/**
 * Created by Administrator on 2017/8/18.
 */
public class Traces {

    /**
     * 时间
     */
    private String AcceptTime;

    /**
     * 描述
     */
    private  String AcceptStation;

    /**
     * 备注
     */
    private String Remark;

    public String getAcceptTime() {
        return AcceptTime;
    }

    public void setAcceptTime(String acceptTime) {
        AcceptTime = acceptTime;
    }

    public String getAcceptStation() {
        return AcceptStation;
    }

    public void setAcceptStation(String acceptStation) {
        AcceptStation = acceptStation;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
