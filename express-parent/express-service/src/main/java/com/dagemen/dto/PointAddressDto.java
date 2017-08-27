package com.dagemen.dto;

import com.dagemen.entity.Company;

import java.util.List;

/**
 * Created by 丁芙蓉 on 2017/8/27.
 */
public class PointAddressDto {

    private long provinceCode;
    private long cityCode;
    private long districtCode;
    private String address;
    private List<Company> companies;

    public PointAddressDto() {
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public long getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(long provinceCode) {
        this.provinceCode = provinceCode;
    }

    public long getCityCode() {
        return cityCode;
    }

    public void setCityCode(long cityCode) {
        this.cityCode = cityCode;
    }

    public long getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(long districtCode) {
        this.districtCode = districtCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
