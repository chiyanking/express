package com.dagemen.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dagemen.dao.UserMapper;
import com.dagemen.domain.UserDO;
import com.dagemen.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Created by kingdom on 2017/8/14.
 */


@Transactional
@Service("accountService")
public class AccountServiceImpl extends ServiceImpl<UserMapper, UserDO> implements AccountService {

    @Resource
    RestTemplate restTemplate;

    public UserDO getUserInform(Long userId) {
        return this.selectById(userId);
    }
}
