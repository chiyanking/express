package com.baomidou.com.dagemen.service.impl;

import com.baomidou.com.dagemen.entity.Company;
import com.baomidou.com.dagemen.mapper.CompanyMapper;
import com.baomidou.com.dagemen.service.MPCompanyService;
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
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements MPCompanyService {
	
}
