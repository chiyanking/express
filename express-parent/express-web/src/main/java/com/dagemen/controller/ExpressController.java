package com.dagemen.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.dagemen.Utils.ApiResultWrapper;
import com.dagemen.dto.ExpressSearchDTO;
import com.dagemen.service.ExpressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by 丁芙蓉 on 2017/8/20.
 */
@Controller
@RequestMapping("/express")
public class ExpressController {

    @Autowired
    private ExpressService expressService;

    /**
     * 获取快递的列表信息，分页
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getExpressList", method = RequestMethod.GET)
    public Map<String, Object> getExpressList(Page page, ExpressSearchDTO search){
        return ApiResultWrapper.success(expressService.getExpressList(page, search));
    }

}
