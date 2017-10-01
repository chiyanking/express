package com.dagemen.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dagemen
 * @since 2017-10-01
 */
@TableName("point_company_relation")
public class PointCompanyRelation extends Model<PointCompanyRelation> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 门店ID
     */
	@TableField("point_id")
	private Long pointId;
    /**
     * 网点名称
     */
	@TableField("point_name")
	private String pointName;
    /**
     * 快递公司Id
     */
	@TableField("company_id")
	private Long companyId;
    /**
     * 快递公司名称
     */
	@TableField("company_name")
	private String companyName;
    /**
     * 电子面单密码
     */
	private String password;
    /**
     * 电子面单账号
     */
	private String account;
    /**
     * 是否电子面单	
     */
	@TableField("is_electronic")
	private Integer isElectronic;
    /**
     * 默认模板的id
     */
	@TableField("exp_model_id")
	private Long expModelId;
    /**
     * 模版名称
     */
	@TableField("exp_model_name")
	private String expModelName;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPointId() {
		return pointId;
	}

	public void setPointId(Long pointId) {
		this.pointId = pointId;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getIsElectronic() {
		return isElectronic;
	}

	public void setIsElectronic(Integer isElectronic) {
		this.isElectronic = isElectronic;
	}

	public Long getExpModelId() {
		return expModelId;
	}

	public void setExpModelId(Long expModelId) {
		this.expModelId = expModelId;
	}

	public String getExpModelName() {
		return expModelName;
	}

	public void setExpModelName(String expModelName) {
		this.expModelName = expModelName;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
