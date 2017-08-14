package com.dagemen.service;

import com.baomidou.mybatisplus.service.IService;
import com.dagemen.domain.UserDO;
import org.springframework.stereotype.Service;

/**
 * Created by kingdom on 2017/8/14.
 */

@Service
public interface AccountService extends IService<UserDO> {

    UserDO getUserInform(Long userId);
}
