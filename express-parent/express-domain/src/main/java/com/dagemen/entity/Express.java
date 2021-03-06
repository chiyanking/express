package com.dagemen.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * <p>
 * 快递信息
 * </p>
 *
 * @author dagemen
 * @since 2017-10-21
 */
public class Express extends Model<Express> {

    private static final long serialVersionUID = 1L;

	@JsonSerialize(using=ToStringSerializer.class)
	private Long id;
    /**
     * 快递单号
     */
	@TableField("exp_no")
	private String expNo;
    /**
     * 订单号
     */
	@TableField("trade_no")
	private String tradeNo;
    /**
     * 门店id
     */
	@TableField("point_id")
	private Long pointId;
    /**
     * 寄件时间
     */
	private Date date;
    /**
     * 快递单状态
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
	private String volume;
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
     * 支付方式： 1-现付，2-到付，3-月结，4-第三方支付
     */
	@TableField("pay_type")
	private Integer payType;
    /**
     * 物品类型,保存格式如下   类型1，类型2，类型3
     */
	@TableField("goods_type")
	private String goodsType;
    /**
     * 物品数量
     */
	@TableField("goods_count")
	private Integer goodsCount;
    /**
     * 备注信息
     */
	private String remark;
    /**
     * 寄件人Id
     */
	@TableField("sender_id")
	private Long senderId;
	@TableField("sender_company")
	private String senderCompany;
    /**
     * 发件人姓名
     */
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
	@JsonSerialize(using=ToStringSerializer.class)
	private Long receiverId;
	@TableField("receiver_company")
	private String receiverCompany;
    /**
     * 发件人姓名
     */
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
     * 快递模版
     */
	@TableField("template_html")
	private String templateHtml;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExpNo() {
		return expNo;
	}

	public void setExpNo(String expNo) {
		this.expNo = expNo;
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

	public String getTemplateHtml() {
		return templateHtml;
	}

	public void setTemplateHtml(String templateHtml) {
		this.templateHtml = templateHtml;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
