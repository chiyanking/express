package com.dagemen.dto;

import com.dagemen.entity.ExpressItem;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class ExpressParam implements Serializable {

	private Long id;
	private String tradeNo;
	private Long pointId;
	private BigDecimal price;
	private Double weight;
	private String volume;
	private Long companyId;
	private String companyName;
	private Long expModelId;
	private Integer payType;
	private String goodsType;
	private Integer goodsCount;
	private String remark;
	@JsonSerialize(using=ToStringSerializer.class)
	private Long senderId;
	private String senderCompany;
	private String senderName;
	private String senderPhone;
	private Long senderProvinceId;
	private Long senderProvinceCode;
	private String senderProvinceName;
	private Long senderCityId;
	private Long senderCityCode;
	private String senderCityName;
	private Long senderDistrictId;
	private Long senderDistrictCode;
	private String senderDistrictName;
	private String senderAddress;

	private Long receiverId;
	private String receiverCompany;
	private String receiverName;
	private String receiverPhone;
	private Long receiverProvinceId;
	private Long receiverProvinceCode;
	private String receiverProvinceName;
	private Long receiverCityId;
	private Long receiverCityCode;
	private String receiverCityName;
	private Long receiverDistrictId;
	private Long receiverDistrictCode;
	private String receiverDistrictName;
	private String receiverAddress;

	List<ExpressItem> expressItems;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Long getPointId() {
		return pointId;
	}

	public void setPointId(Long pointId) {
		this.pointId = pointId;
	}


	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
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

	public Long getExpModelId() {
		return expModelId;
	}

	public void setExpModelId(Long expModelId) {
		this.expModelId = expModelId;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public String getSenderCompany() {
		return senderCompany;
	}

	public void setSenderCompany(String senderCompany) {
		this.senderCompany = senderCompany;
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

	public Long getSenderProvinceId() {
		return senderProvinceId;
	}

	public void setSenderProvinceId(Long senderProvinceId) {
		this.senderProvinceId = senderProvinceId;
	}

	public Long getSenderProvinceCode() {
		return senderProvinceCode;
	}

	public void setSenderProvinceCode(Long senderProvinceCode) {
		this.senderProvinceCode = senderProvinceCode;
	}

	public String getSenderProvinceName() {
		return senderProvinceName;
	}

	public void setSenderProvinceName(String senderProvinceName) {
		this.senderProvinceName = senderProvinceName;
	}

	public Long getSenderCityId() {
		return senderCityId;
	}

	public void setSenderCityId(Long senderCityId) {
		this.senderCityId = senderCityId;
	}

	public Long getSenderCityCode() {
		return senderCityCode;
	}

	public void setSenderCityCode(Long senderCityCode) {
		this.senderCityCode = senderCityCode;
	}

	public String getSenderCityName() {
		return senderCityName;
	}

	public void setSenderCityName(String senderCityName) {
		this.senderCityName = senderCityName;
	}

	public Long getSenderDistrictId() {
		return senderDistrictId;
	}

	public void setSenderDistrictId(Long senderDistrictId) {
		this.senderDistrictId = senderDistrictId;
	}

	public Long getSenderDistrictCode() {
		return senderDistrictCode;
	}

	public void setSenderDistrictCode(Long senderDistrictCode) {
		this.senderDistrictCode = senderDistrictCode;
	}

	public String getSenderDistrictName() {
		return senderDistrictName;
	}

	public void setSenderDistrictName(String senderDistrictName) {
		this.senderDistrictName = senderDistrictName;
	}

	public String getSenderAddress() {
		return senderAddress;
	}

	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverCompany() {
		return receiverCompany;
	}

	public void setReceiverCompany(String receiverCompany) {
		this.receiverCompany = receiverCompany;
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

	public Long getReceiverProvinceId() {
		return receiverProvinceId;
	}

	public void setReceiverProvinceId(Long receiverProvinceId) {
		this.receiverProvinceId = receiverProvinceId;
	}

	public Long getReceiverProvinceCode() {
		return receiverProvinceCode;
	}

	public void setReceiverProvinceCode(Long receiverProvinceCode) {
		this.receiverProvinceCode = receiverProvinceCode;
	}

	public String getReceiverProvinceName() {
		return receiverProvinceName;
	}

	public void setReceiverProvinceName(String receiverProvinceName) {
		this.receiverProvinceName = receiverProvinceName;
	}

	public Long getReceiverCityId() {
		return receiverCityId;
	}

	public void setReceiverCityId(Long receiverCityId) {
		this.receiverCityId = receiverCityId;
	}

	public Long getReceiverCityCode() {
		return receiverCityCode;
	}

	public void setReceiverCityCode(Long receiverCityCode) {
		this.receiverCityCode = receiverCityCode;
	}

	public String getReceiverCityName() {
		return receiverCityName;
	}

	public void setReceiverCityName(String receiverCityName) {
		this.receiverCityName = receiverCityName;
	}

	public Long getReceiverDistrictId() {
		return receiverDistrictId;
	}

	public void setReceiverDistrictId(Long receiverDistrictId) {
		this.receiverDistrictId = receiverDistrictId;
	}

	public Long getReceiverDistrictCode() {
		return receiverDistrictCode;
	}

	public void setReceiverDistrictCode(Long receiverDistrictCode) {
		this.receiverDistrictCode = receiverDistrictCode;
	}

	public String getReceiverDistrictName() {
		return receiverDistrictName;
	}

	public void setReceiverDistrictName(String receiverDistrictName) {
		this.receiverDistrictName = receiverDistrictName;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public List<ExpressItem> getExpressItems() {
		return expressItems;
	}

	public void setExpressItems(List<ExpressItem> expressItems) {
		this.expressItems = expressItems;
	}
}
