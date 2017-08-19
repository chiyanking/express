package com.dagemen.entity;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dagemen
 * @since 2017-08-19
 */
public class Express extends Model<Express> {

    private static final long serialVersionUID = 1L;

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
	private Date date;
    /**
     * 是否打印
     */
	@TableField("is_print")
	private Integer isPrint;
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
	@TableField("sender_name")
	private String senderName;
	@TableField("sender_provice_id")
	private Long senderProviceId;
	@TableField("sender_provice_name")
	private String senderProviceName;
	@TableField("sender_city_id")
	private Long senderCityId;
	@TableField("sender_city_name")
	private String senderCityName;
	@TableField("sender_district_id")
	private Long senderDistrictId;
	@TableField("sender_district_name")
	private String senderDistrictName;
	@TableField("sender_address")
	private String senderAddress;
	@TableField("receiver_id")
	private Long receiverId;
    /**
     * 发件人姓名
     */
	@TableField("receiver_name")
	private String receiverName;
	@TableField("receiver_provice_id")
	private Long receiverProviceId;
	@TableField("receiver_provice_name")
	private String receiverProviceName;
	@TableField("receiver_city_id")
	private Long receiverCityId;
	@TableField("receiver_city_name")
	private String receiverCityName;
	@TableField("receiver_district_id")
	private Long receiverDistrictId;
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

	public Integer getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(Integer isPrint) {
		this.isPrint = isPrint;
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

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public Long getSenderProviceId() {
		return senderProviceId;
	}

	public void setSenderProviceId(Long senderProviceId) {
		this.senderProviceId = senderProviceId;
	}

	public String getSenderProviceName() {
		return senderProviceName;
	}

	public void setSenderProviceName(String senderProviceName) {
		this.senderProviceName = senderProviceName;
	}

	public Long getSenderCityId() {
		return senderCityId;
	}

	public void setSenderCityId(Long senderCityId) {
		this.senderCityId = senderCityId;
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

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public Long getReceiverProviceId() {
		return receiverProviceId;
	}

	public void setReceiverProviceId(Long receiverProviceId) {
		this.receiverProviceId = receiverProviceId;
	}

	public String getReceiverProviceName() {
		return receiverProviceName;
	}

	public void setReceiverProviceName(String receiverProviceName) {
		this.receiverProviceName = receiverProviceName;
	}

	public Long getReceiverCityId() {
		return receiverCityId;
	}

	public void setReceiverCityId(Long receiverCityId) {
		this.receiverCityId = receiverCityId;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
