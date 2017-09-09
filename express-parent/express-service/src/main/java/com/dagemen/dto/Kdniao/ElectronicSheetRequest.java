package com.dagemen.dto.Kdniao;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/5.
 * 快递鸟的电子面单RequestData实体
 */
public class ElectronicSheetRequest {

    /**
     * 接收者
     */
    private Receiver Receiver;
    /**
     * 寄件者
     */
    private Sender Sender;
    /**
     * 商品信息
     */
    private List<Commodity> Commodity;
    /**
     * 必填
     * 快件类型 1 标准快件
     */
    private Integer ExpType;
    /**
     * 必填
     * 付款方式：1 寄方付 2 收方付
     */
    private Integer PayType;
    /**
     * 必填
     * 快递公司编码
     */
    private String ShipperCode;
    /**
     * 必填
     * 订单编号
     */
    private String OrderCode;

    /**
     * 返回电子面单 0 不需要 1 需要
     */
    private Integer IsReturnPrintTemplate;

    /**
     * 电子面单客户账号
     */
    private String CustomerName;
    /**
     * 电子面单密码
     */
    private String CustomerPwd;
    /**
     * 是否通知快递员上门取件 0 通知 1 不通知
     */
    private Integer IsNotice;
    /**
     * 用户自定义回调信息
     */
    private String CallBack;
    /**
     * 会员标识
     */
    private String MemberID;
    /**
     * 收件网点标识
     */
    private String SendSite;
    /**
     * 快递单号
     */
    private String LogisticCode;
    /**
     * 寄件费（运费）
     */
    private Double Cost;
    /**
     * 其他费用
     */
    private Double OtherCost;
    /**
     * 上门取货时间段
     */
    private String StartDate;
    /**
     * 上门取货时间段
     */
    private String EndDate;
    /**
     * 物品总重量
     */
    private Double Weight;
    /**
     * 件数、包裹数
     */
    private Integer Quantity;
    /**
     * 物品总体积
     */
    private Double Volume;
    /**
     * 备注
     */
    private String Remark;
    /**
     * 月结编码
     */
    private String MonthCode;

    public ElectronicSheetRequest() {
        Commodity = new ArrayList<>();
    }

    @JSONField(name = "Receiver")
    public Receiver getReceiver() {
        return Receiver;
    }

    public void setReceiver(Receiver receiver) {
        Receiver = receiver;
    }

    @JSONField(name = "Sender")
    public Sender getSender() {
        return Sender;
    }

    public void setSender(Sender sender) {
        Sender = sender;
    }

    @JSONField(name = "Commodity")
    public List<Commodity> getCommodity() {
        return Commodity;
    }

    public void setCommodity(Commodity commodity) {
        Commodity.add(commodity);
    }

    @JSONField(name = "ExpType")
    public Integer getExpType() {
        return ExpType;
    }

    public void setExpType(Integer expType) {
        ExpType = expType;
    }


    @JSONField(name = "ShipperCode")
    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String shipperCode) {
        ShipperCode = shipperCode;
    }

    @JSONField(name = "OrderCode")
    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    @JSONField(name = "IsReturnPrintTemplate")
    public Integer getIsReturnPrintTemplate() {
        return IsReturnPrintTemplate;
    }

    public void setIsReturnPrintTemplate(Integer isReturnPrintTemplate) {
        IsReturnPrintTemplate = isReturnPrintTemplate;
    }

    @JSONField(name = "CustomerName")
    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    @JSONField(name = "CustomerPwd")
    public String getCustomerPwd() {
        return CustomerPwd;
    }

    public void setCustomerPwd(String customerPwd) {
        CustomerPwd = customerPwd;
    }

    @JSONField(name = "CallBack")
    public String getCallBack() {
        return CallBack;
    }

    public void setCallBack(String callBack) {
        CallBack = callBack;
    }

    @JSONField(name = "MemberID")
    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String memberID) {
        MemberID = memberID;
    }

    @JSONField(name = "SendSite")
    public String getSendSite() {
        return SendSite;
    }

    public void setSendSite(String sendSite) {
        SendSite = sendSite;
    }

    @JSONField(name = "LogisticCode")
    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        LogisticCode = logisticCode;
    }


    @JSONField(name = "StartDate")
    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    @JSONField(name = "EndDate")
    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    @JSONField(name = "Remark")
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    @JSONField(name = "MonthCode")
    public String getMonthCode() {
        return MonthCode;
    }

    public void setMonthCode(String monthCode) {
        MonthCode = monthCode;
    }

//    ***************************************************

    @JSONField(name = "PayType")
    public Integer getPayType() {
        return PayType;
    }
    public void setPayType(Integer payType) {
        PayType = payType;
    }
    @JSONField(name = "IsNotice")
    public Integer getIsNotice() {
        return IsNotice;
    }
    public void setIsNotice(Integer isNotice) {
        IsNotice = isNotice;
    }
    @JSONField(name = "Cost")
    public Double getCost() {
        return Cost;
    }
    public void setCost(Double cost) {
        Cost = cost;
    }
    @JSONField(name = "OtherCost")
    public Double getOtherCost() {
        return OtherCost;
    }
    public void setOtherCost(Double otherCost) {
        OtherCost = otherCost;
    }
    @JSONField(name = "Weight")
    public Double getWeight() {
        return Weight;
    }

    public void setWeight(Double weight) {
        Weight = weight;
    }
    @JSONField(name = "MonthCode")
    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    @JSONField(name = "Volume")
    public Double getVolume() {
        return Volume;
    }

    public void setVolume(Double volume) {
        Volume = volume;
    }
}
