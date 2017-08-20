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
 * @since 2017-08-19
 */
@TableName("exp_model")
public class ExpModel extends Model<ExpModel> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 快递公司id
     */
	@TableField("company_id")
	private Long companyId;
    /**
     * 快递单子模板名称
     */
	@TableField("exp_model_name")
	private String expModelName;
    /**
     * 模板路径
     */
	@TableField("exp_model_url")
	private String expModelUrl;
    /**
     * 状态 0：正常  1：废除状态
     */
	@TableField("exp_model_status")
	private Integer expModelStatus;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getExpModelName() {
		return expModelName;
	}

	public void setExpModelName(String expModelName) {
		this.expModelName = expModelName;
	}

	public String getExpModelUrl() {
		return expModelUrl;
	}

	public void setExpModelUrl(String expModelUrl) {
		this.expModelUrl = expModelUrl;
	}

	public Integer getExpModelStatus() {
		return expModelStatus;
	}

	public void setExpModelStatus(Integer expModelStatus) {
		this.expModelStatus = expModelStatus;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
