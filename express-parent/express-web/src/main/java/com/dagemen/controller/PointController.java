package com.dagemen.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.Utils.ApiResultWrapper;
import com.dagemen.entity.Point;
import com.dagemen.entity.User;
import com.dagemen.exception.ApiException;
import com.dagemen.exception.ApiExceptionEnum;
import com.dagemen.service.PointService;
import com.dagemen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by 丁芙蓉 on 2017/8/19.
 */
@Controller
@RequestMapping("/point")
public class PointController {

    @Autowired
    private PointService pointService;

    /**
     * 登录验证
     * @param point
     * @return
     */
    @RequestMapping("/checkLogin")
    @ResponseBody
    public Object checkLogin(Point point) {
        pointService.checkLogin(point);
        return ApiResultWrapper.success("成功");
    }
}
