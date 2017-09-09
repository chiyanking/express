package com.dagemen.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author dagemen
 * @since 2017-08-27
 */
public class Express extends Model<Express> {

    private static final long serialVersionUID = 1L;

	@JsonSerialize(using=ToStringSerializer.class)
	private Long id;
    /**
     * 快递单号
     */
	@TableField("exp_code")
	private String expCode;
    /**
     * 门店id
     */
	@TableField("point_id")
	private Long pointId;
    /**
     * 寄件时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	private Date date;
    /**
     * 是否打印
     */
	private Integer status;
    /**
     * 快递价格
     */
	private BigDecimal price;
    /**
     * 包裹重量
     */
	private Double weight;
    /**
     * 寄件人Id
     */
	@TableField("sender_id")
	private Long senderId;
    /**
     * 发件人姓名
     */
    @TableField("sender_company")
    private String senderCompany;
	@TableField("sender_name")
	private String senderName;
	@TableField("sender_phone")
	private String senderPhone;
	@TableField("sender_province_id")
	private Long senderProvinceId;
	@TableField("sender_province_code")
	private Long senderProvinceCode;
	@TableField("sender_province_name")
	private String senderProvinceName;
	@TableField("sender_city_id")
	private Long senderCityId;
	@TableField("sender_city_code")
	private Long senderCityCode;
	@TableField("sender_city_name")
	private String senderCityName;
	@TableField("sender_district_id")
	private Long senderDistrictId;
	@TableField("sender_district_code")
	private Long senderDistrictCode;
	@TableField("sender_district_name")
	private String senderDistrictName;
	@TableField("sender_address")
	private String senderAddress;
	@TableField("receiver_id")
	private Long receiverId;
    /**
     * 发件人姓名
     */
	@TableField("receiver_company")
	private String receiverCompany;
	@TableField("receiver_name")
	private String receiverName;
	@TableField("receiver_phone")
	private String receiverPhone;
	@TableField("receiver_province_id")
	private Long receiverProvinceId;
	@TableField("receiver_province_code")
	private Long receiverProvinceCode;
	@TableField("receiver_province_name")
	private String receiverProvinceName;
	@TableField("receiver_city_id")
	private Long receiverCityId;
	@TableField("receiver_city_code")
	private Long receiverCityCode;
	@TableField("receiver_city_name")
	private String receiverCityName;
	@TableField("receiver_district_id")
	private Long receiverDistrictId;
	@TableField("receiver_district_code")
	private Long receiverDistrictCode;
	@TableField("receiver_district_name")
	private String receiverDistrictName;
	@TableField("receiver_address")
	private String receiverAddress;
    /**
     * 寄件所使用快递Id
     */
	@TableField("company_id")
	private Long companyId;
	@TableField("company_name")
	private String companyName;
    /**
     * 快递模板Id
     */
	@TableField("exp_model_id")
	private Long expModelId;
    /**
     * 支付方式： 0：寄付现结  1：到付
     */
	@TableField("pay_type")
	private Integer payType;
    /**
     * 物品类型,保存格式如下   类型1，类型2，类型3
     */
	@TableField("goods_style")
	private String goodsStyle;
    /**
     * 物品数量
     */
	@TableField("goods_count")
	private Integer goodsCount;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getpayType() {
		return payType;
	}

	public void setpayType(Integer payType) {
		this.payType = payType;
	}

	public String getGoodsStyle() {
		return goodsStyle;
	}

	public void setGoodsStyle(String goodsStyle) {
		this.goodsStyle = goodsStyle;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
