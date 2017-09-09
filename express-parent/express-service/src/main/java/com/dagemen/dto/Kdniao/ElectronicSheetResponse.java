package com.dagemen.dto.Kdniao;

/**
 * Created by Administrator on 2017/9/5.
 */
public class ElectronicSheetResponse {

    /**
     * 用户ID
     */
    private String EBusinessID;

    /**
     * 成功与否
     */
    private Boolean Success;

    /**
     * 错误编码
     */
    private String ResultCode;

    /**
     * 失败原因
     */
    private String Reason;

    /**
     * 唯一标识
     */
    private String UniquerRequestNumber;

    /**
     * 面单打印模板
     */
    private String PrintTemplate;

    /**
     * 订单预计到货时间yyyy-mm-dd
     */
    private String EstimatedDeliveryTime;

    /**
     * 用户自定义回调信息
     */
    private String Callback;

    /**
     * 子单数量
     */
    private Integer SubCount;

    /**
     * 子单号
     */
    private String SubOrders;

    /**
     * 子单模板
     */
    private String SubPrintTemplates;

    /**
     * 订单信息
     */
    private Order order;

    public String getEBusinessID() {
        return EBusinessID;
    }

    public void setEBusinessID(String EBusinessID) {
        this.EBusinessID = EBusinessID;
    }

    public Boolean getSuccess() {
        return Success;
    }

    public void setSuccess(Boolean success) {
        Success = success;
    }

    public String getResultCode() {
        return ResultCode;
    }

    public void setResultCode(String resultCode) {
        ResultCode = resultCode;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getUniquerRequestNumber() {
        return UniquerRequestNumber;
    }

    public void setUniquerRequestNumber(String uniquerRequestNumber) {
        UniquerRequestNumber = uniquerRequestNumber;
    }

    public String getPrintTemplate() {
        return PrintTemplate;
    }

    public void setPrintTemplate(String printTemplate) {
        PrintTemplate = printTemplate;
    }

    public String getEstimatedDeliveryTime() {
        return EstimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(String estimatedDeliveryTime) {
        EstimatedDeliveryTime = estimatedDeliveryTime;
    }

    public String getCallback() {
        return Callback;
    }

    public void setCallback(String callback) {
        Callback = callback;
    }

    public Integer getSubCount() {
        return SubCount;
    }

    public void setSubCount(Integer subCount) {
        SubCount = subCount;
    }

    public String getSubOrders() {
        return SubOrders;
    }

    public void setSubOrders(String subOrders) {
        SubOrders = subOrders;
    }

    public String getSubPrintTemplates() {
        return SubPrintTemplates;
    }

    public void setSubPrintTemplates(String subPrintTemplates) {
        SubPrintTemplates = subPrintTemplates;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
