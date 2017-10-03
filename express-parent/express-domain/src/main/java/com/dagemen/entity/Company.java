package com.dagemen.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dagemen
 * @since 2017-08-19
 */
public class Company extends Model<Company> {

    private static final long serialVersionUID = 1L;

    /**
     * 快递Id
     */
    @TableId(type = IdType.AUTO)
	private Long id;
    /**
     * 快递编号
     */
	private String code;
    /**
     * 快递名称
     */
	private String name;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
