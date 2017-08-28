package com.dagemen.dto;

import com.dagemen.entity.Company;

import java.util.List;

/**
 * Created by 丁芙蓉 on 2017/8/27.
 */
public class PointAddressDTO {

    private Long provinceCode;
    private Long districtCode;
    private Long cityCode;
    private String address;
    private String name;
    private List<Company> companies;

    public PointAddressDTO() {
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public Long getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Long provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Long getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(Long districtCode) {
        this.districtCode = districtCode;
    }

    public Long getCityCode() {
        return cityCode;
    }

    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
