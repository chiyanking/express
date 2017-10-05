package com.dagemen.dto;

import java.util.List;

/**
 * 手机端用户传输过来的快递信息
 */
public class ExpressDTO {

    /**
     * 门店Id
     */
    private Long pointId;

    private Long companyId;
    private String companyName;
    private Double weight;
    private String itemName;

    private String senderName;
    private String senderPhone;
    private List<Long> senderPCDCode;
    private List<String> senderPCDName;
    private String senderAddress;

    private String receiverName;
    private String receiverPhone;
    private List<Long> receiverPCDCode;
    private List<String> receiverPCDName;
    private String receiverAddress;

    public Long getPointId() {
        return pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public List<Long> getSenderPCDCode() {
        return senderPCDCode;
    }

    public void setSenderPCDCode(List<Long> senderPCDCode) {
        this.senderPCDCode = senderPCDCode;
    }

    public List<String> getSenderPCDName() {
        return senderPCDName;
    }

    public void setSenderPCDName(List<String> senderPCDName) {
        this.senderPCDName = senderPCDName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public List<Long> getReceiverPCDCode() {
        return receiverPCDCode;
    }

    public void setReceiverPCDCode(List<Long> receiverPCDCode) {
        this.receiverPCDCode = receiverPCDCode;
    }

    public List<String> getReceiverPCDName() {
        return receiverPCDName;
    }

    public void setReceiverPCDName(List<String> receiverPCDName) {
        this.receiverPCDName = receiverPCDName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
}
