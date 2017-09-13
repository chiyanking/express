package com.dagemen.dto.Kdniao;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2017/9/5.
 * 寄件人信息
 */
public class Sender {

    /**
     * 姓名
     */
    private String Name;
    /**
     * 电话
     */
    private String Mobile;
    /**
     * 省
     */
    private String ProvinceName;
    /**
     * 市
     */
    private String CityName;
    /**
     * 区
     */
    private String ExpAreaName;
    /**
     * 地址
     */
    private String Address;

    /**
     * 电话与手机，必填一个
     */
    private String Tel;

    /**
     * 收件人公司
     */
    private String Company;

    /**
     * 收件人邮编
     */
    private String PostCode;

    @JSONField(name = "Address")
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @JSONField(name = "CityName")
    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    @JSONField(name = "ExpAreaName")
    public String getExpAreaName() {
        return ExpAreaName;
    }

    public void setExpAreaName(String expAreaName) {
        ExpAreaName = expAreaName;
    }

    @JSONField(name = "Mobile")
    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    @JSONField(name = "Name")
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name == null ? "": name;
    }

    @JSONField(name = "ProvinceName")
    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }

    @JSONField(name = "Tel")
    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }
    @JSONField(name = "Company")
    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }
    @JSONField(name = "PostCode")
    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String postCode) {
        PostCode = postCode;
    }
}
