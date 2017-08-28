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
 * @since 2017-08-27
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
     * 快递公司Id
     */
	@TableField("company_id")
	private Long companyId;
    /**
     * 默认模板的id
     */
	@TableField("exp_model_id")
	private Long expModelId;


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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
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
