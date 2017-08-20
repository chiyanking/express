package com.dagemen.controller;

import com.dagemen.dto.WechatSignature;
import com.dagemen.Utils.ApiResultWrapper;
import com.dagemen.helper.TokenHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/proxy")
public class ProxyController {

    @Resource
    TokenHelper tokenHelper;

    @ResponseBody
    @RequestMapping(value = "signature", method = RequestMethod.GET)
    public Map<String, Object> getSignature(String url) throws IOException {
        WechatSignature signature = tokenHelper.getSignature(url);
        return ApiResultWrapper.success(signature);
    }
}
