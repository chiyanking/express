package com.dagemen.dto.Kdniao;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2017/9/5.
 */
public class AddService {

    /**
     * 增值服务名称
     */
    private String Name;

    /**
     * 增值服务值
     */
    private String Value;

    /**
     * 客户标识（选填）
     */
    private String CustomerID;

    @JSONField(name = "Name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @JSONField(name = "Value")
    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    @JSONField(name = "CustomerID")
    public String getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(String customerID) {
        CustomerID = customerID;
    }
}
