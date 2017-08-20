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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    @ResponseBody
    public Object checkLogin(Point point, HttpSession httpSession) {
        pointService.checkLogin(point, httpSession);
        return ApiResultWrapper.success("成功");
    }

    /**
     * 登出系统
     * @param httpSession
     * @return
     */
    @RequestMapping("/logOut")
    @ResponseBody
    public Object logOut(HttpSession httpSession){
        pointService.logOut(httpSession);
        return ApiResultWrapper.success("登出成功");
    }

}
