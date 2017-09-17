package com.dagemen.controller;

import com.dagemen.Utils.ApiResultWrapper;
import com.dagemen.authorization.AuthorizeAnnotation;
import com.dagemen.dto.ExpressDTO;
import com.dagemen.dto.WechatSignature;
import com.dagemen.helper.TokenHelper;
import com.dagemen.service.MobileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

/**
 * Created by kingdom on 2017/8/14.
 */

@Controller
@RequestMapping("/mobile")
public class MobileController {


    @Resource
    MobileService mobileService;


    @Resource
    TokenHelper tokenHelper;

    @ResponseBody
    @AuthorizeAnnotation(isLogin = false)
    @RequestMapping(value = "signature", method = RequestMethod.GET)
    public Map<String, Object> getSignature(String url) throws IOException {
        WechatSignature signature = tokenHelper.getSignature(url);
        return ApiResultWrapper.success(signature);
    }

    @ResponseBody
    @AuthorizeAnnotation(isLogin = false)
    @RequestMapping(value = "insertExpress", method = RequestMethod.POST)
    public Map<String, Object> getAccount(@RequestBody ExpressDTO express) {
        return ApiResultWrapper.success(mobileService.insertExpress(express));
    }

    @ResponseBody
    @AuthorizeAnnotation(isLogin = false)
    @RequestMapping(value = "getPointInformation", method = RequestMethod.GET)
    public Map<String, Object> getPointInformation(Long pointId) {
        return ApiResultWrapper.success(mobileService.getPointInformation(pointId));
    }
}
