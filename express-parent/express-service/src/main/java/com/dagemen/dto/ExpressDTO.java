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

    private String senderName;
    private String senderPhone;
    private List<Long> senderPCD;
    private String senderAddress;

    private String receiverName;
    private String receiverPhone;
    private List<Long> receiverPCD;
    private String receiverAddress;

    public Long getPointId() {
        return pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
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

    public List<Long> getSenderPCD() {
        return senderPCD;
    }

    public void setSenderPCD(List<Long> senderPCD) {
        this.senderPCD = senderPCD;
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

    public List<Long> getReceiverPCD() {
        return receiverPCD;
    }

    public void setReceiverPCD(List<Long> receiverPCD) {
        this.receiverPCD = receiverPCD;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }
}
