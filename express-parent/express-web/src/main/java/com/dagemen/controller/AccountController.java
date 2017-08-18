package com.dagemen.controller;

import com.dagemen.Utils.ApiResultWrapper;
import com.dagemen.domain.UserDO;
import com.dagemen.service.AccountService;
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
    AccountService accountService;

    /**
     *
     */
    @ResponseBody
    @RequestMapping(value = "getAccountInform", method = RequestMethod.GET)
    public Map<String, Object> getAccount(Long userId) {
        UserDO userInform = accountService.getUserInform(userId);
        return ApiResultWrapper.success(userInform);
    }
}
