package com.baomidou.com.dagemen.entity;

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
public class Point extends Model<Point> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 门店名称
     */
	private String name;
    /**
     * 手机号
     */
	private String phone;
    /**
     * （手机号作为账号）登陆密码
     */
	private String password;
    /**
     * 门店所在的省份
     */
	@TableField("province_id")
	private Long provinceId;
	@TableField("province_code")
	private Long provinceCode;
    /**
     * 门店所在市级Id
     */
	@TableField("city_id")
	private Long cityId;
	@TableField("city_code")
	private Long cityCode;
    /**
     * 门店状态
     */
	private Integer status;
    /**
     * 门店所在区域
     */
	@TableField("district_id")
	private Long districtId;
	@TableField("district_code")
	private Long districtCode;
    /**
     * 详细地址
     */
	private String address;


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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
