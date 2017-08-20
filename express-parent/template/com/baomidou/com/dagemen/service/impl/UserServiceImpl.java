package com.baomidou.com.dagemen.service.impl;

import com.baomidou.com.dagemen.entity.User;
import com.baomidou.com.dagemen.mapper.UserMapper;
import com.baomidou.com.dagemen.service.MPUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dagemen
 * @since 2017-08-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements MPUserService {
	
}
