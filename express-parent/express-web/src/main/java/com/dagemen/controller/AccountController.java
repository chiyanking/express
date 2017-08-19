package com.dagemen.controller;

import com.dagemen.Utils.ApiResultWrapper;
import com.dagemen.entity.User;
import com.dagemen.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by kingdom on 2017/8/14.
 */

@Controller
@RequestMapping("/account")
public class AccountController {


    @Resource
    UserService userService;

    /**
     *
     */
    @ResponseBody
    @RequestMapping(value = "getAccountInform", method = RequestMethod.GET)
    public Map<String, Object> getAccount(Long userId) {

        User user = userService.selectById(userId);
        return ApiResultWrapper.success(user);
    }
}
