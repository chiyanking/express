package com.baomidou.com.dagemen.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 行政区域：省份
 * </p>
 *
 * @author dagemen
 * @since 2017-08-20
 */
@TableName("region_province")
public class RegionProvince extends Model<RegionProvince> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 省份名称
     */
	private String name;
    /**
     * 省份code
     */
	private Long code;


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

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
