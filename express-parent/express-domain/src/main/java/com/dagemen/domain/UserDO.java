package com.dagemen.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * Created by kingdom on 2017/8/14.
 */
@TableName
public class UserDO {
    /**
     * 主键ID
     */
    @TableId
    private Long id;

    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号码
     */
    private String phone;

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
}
