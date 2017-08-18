package com.dagemen.model.kdniao;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */
public class KdniaoModel {

    /**
     * 用户ID
     */
    private String EBusinessID;

    /**
     * 订单编号
     */
    private String OrderCode;

    /**
     * 快递公司编码
     */
    private String ShipperCode;

    /**
     * 物流运单号
     */
    private String LogisticCode;

    /**
     * 失败原因
     */
    private String Reason;

    /**
     * 物流状态：2-在途中,3-签收,4-问题件
     */
    private String State;

    /**
     * 成功与否
     */
    private boolean Success;

    /**
     *
     */
    private List<com.dagemen.model.kdniao.Traces> Traces;

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String shipperCode) {
        ShipperCode = shipperCode;
    }

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        LogisticCode = logisticCode;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public List<Traces> getTraces() {
        return Traces;
    }

    public void setTraces(List<Traces> traces) {
        Traces = traces;
    }
}
