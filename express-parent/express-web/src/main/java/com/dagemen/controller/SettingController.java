package com.dagemen.controller;

import com.dagemen.Utils.ApiResultWrapper;
import com.dagemen.service.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/setting")
public class SettingController {


    @Resource
    CompanyService companyService;
    /**
     * 获取当前门店有的快递
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllCompanies", method = RequestMethod.GET)
    public Map<String, Object> getExpressList() {
        return ApiResultWrapper.success(companyService.getAllCompanies());
    }

    @ResponseBody
    @RequestMapping(value = "/getHasCompanies", method = RequestMethod.GET)
    public Map<String,Object> getCompanyList(){
        return ApiResultWrapper.success(companyService.getHasCompanies());
    }

    @ResponseBody
    @RequestMapping(value = "/getModels", method = RequestMethod.GET)
    public Map<String,Object> getCompanyList(Long compnayId){
        return ApiResultWrapper.success(companyService.getModels(compnayId));
    }
}

