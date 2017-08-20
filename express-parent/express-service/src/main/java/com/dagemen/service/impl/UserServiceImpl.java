package com.dagemen.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dagemen.dao.UserMapper;
import com.dagemen.entity.User;
import com.dagemen.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dagemen
 * @since 2017-08-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
