package com.dagemen.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 快递物品
 * </p>
 *
 * @author dagemen
 * @since 2017-10-02
 */
@TableName("express_item")
public class ExpressItem extends Model<ExpressItem> {

    private static final long serialVersionUID = 1L;

    /**
     * 物品Id
     */
	private Long id;
    /**
     * 物品名称
     */
	@TableField("item_name")
	private String itemName;
    /**
     * 数量
     */
	@TableField("item_num")
	private Integer itemNum;
    /**
     * 物品重量
     */
	@TableField("item_wight")
	private Integer itemWight;
    /**
     * 快递Id
     */
	@TableField("exp_id")
	private Long expId;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public Integer getItemWight() {
		return itemWight;
	}

	public void setItemWight(Integer itemWight) {
		this.itemWight = itemWight;
	}

	public Long getExpId() {
		return expId;
	}

	public void setExpId(Long expId) {
		this.expId = expId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
