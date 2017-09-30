package com.dagemen.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.dagemen.Utils.ApiResultWrapper;
import com.dagemen.authorization.AuthorizeAnnotation;
import com.dagemen.dto.ExpressSearchDTO;
import com.dagemen.dto.PointUpdateCompanyDTO;
import com.dagemen.entity.Express;
import com.dagemen.entity.Point;
import com.dagemen.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
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

