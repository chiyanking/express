package com.dagemen.entity;

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
 * @since 2017-08-27
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private String phone;
    /**
     * 微信认证中心
     */
	@TableField("app_id")
	private Long appId;
	@TableField("province_id")
	private Long provinceId;
	@TableField("province_code")
	private Long provinceCode;
	@TableField("province_name")
	private String provinceName;
	@TableField("city_id")
	private Long cityId;
	@TableField("city_code")
	private Long cityCode;
	@TableField("city_name")
	private String cityName;
	@TableField("district_id")
	private Long districtId;
	@TableField("district_code")
	private Long districtCode;
	@TableField("district_name")
	private String districtName;
	private String address;
    /**
     * 最后寄件时间
     */
	@TableField("last_send_date")
	private Date lastSendDate;
    /**
     * 寄件次数
     */
	@TableField("send_times")
	private Integer sendTimes;
    /**
     * 最后收件时间
     */
	@TableField("last_receive_date")
	private Date lastReceiveDate;
    /**
     * 收件次数
     */
	@TableField("receive_times")
	private Integer receiveTimes;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(Long provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getCityCode() {
		return cityCode;
	}

	public void setCityCode(Long cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(Long districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getLastSendDate() {
		return lastSendDate;
	}

	public void setLastSendDate(Date lastSendDate) {
		this.lastSendDate = lastSendDate;
	}

	public Integer getSendTimes() {
		return sendTimes;
	}

	public void setSendTimes(Integer sendTimes) {
		this.sendTimes = sendTimes;
	}

	public Date getLastReceiveDate() {
		return lastReceiveDate;
	}

	public void setLastReceiveDate(Date lastReceiveDate) {
		this.lastReceiveDate = lastReceiveDate;
	}

	public Integer getReceiveTimes() {
		return receiveTimes;
	}

	public void setReceiveTimes(Integer receiveTimes) {
		this.receiveTimes = receiveTimes;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
