package com.dagemen;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.entity.Company;
import com.dagemen.service.CompanyService;
import com.dagemen.service.kuaidiniao.CompanyCode;

import javax.annotation.Resource;

public class CompanyServiceTest extends Test{
    @Resource
    CompanyService companyService;


    @org.junit.Test
    public void insertOrUpdate(){
        CompanyCode.companyCode.forEach((key,value)->{

            Company company = new Company();
            company.setCode(value);
            EntityWrapper wrapper = new EntityWrapper<>(company);
            int i = companyService.selectCount(wrapper);
            if(i>0){
                return ;
            }
            Company co = new Company();
            co.setCode(value);
            co.setName(key);
            companyService.insert(co);
        });
    }
}
