package com.dagemen.controller;

import com.dagemen.Utils.ApiResultWrapper;
import com.dagemen.authorization.AuthorizeAnnotation;
import com.dagemen.dto.ExpressDTO;
import com.dagemen.entity.User;
import com.dagemen.service.MobileService;
import com.dagemen.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by kingdom on 2017/8/14.
 */

@Controller
@RequestMapping("/mobile")
public class MobileController {


    @Resource
    MobileService mobileService;

    @ResponseBody
    @AuthorizeAnnotation(isLogin = false)
    @RequestMapping(value = "insertExpress", method = RequestMethod.POST)
    public Map<String, Object> getAccount(@RequestBody ExpressDTO express) {
        return ApiResultWrapper.success(mobileService.insertExpress(express));
    }
}
