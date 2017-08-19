package com.dagemen.service.impl;

import com.dagemen.dao.CompanyMapper;
import com.dagemen.entity.Company;
import com.dagemen.service.CompanyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dagemen
 * @since 2017-08-19
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {
	
}
