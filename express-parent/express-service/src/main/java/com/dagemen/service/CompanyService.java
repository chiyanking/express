package com.dagemen.service;

import com.dagemen.entity.Company;
import com.baomidou.mybatisplus.service.IService;
import com.dagemen.entity.ExpModel;
import com.dagemen.entity.PointCompanyRelation;
import com.dagemen.enums.LabelValue;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dagemen
 * @since 2017-08-19
 */
public interface CompanyService extends IService<Company> {
    /**
     * 获取所有的快递
     * @return
     */
	List<LabelValue> getAllCompanies();

    /**
     * 获取本门店绑定的快递
     * @return
     */
	List<PointCompanyRelation> getHasCompanies();
    /**
     * 获取我们配置的公司的纸质快递模版
     * @param companyId 公司Id
     */
    List<ExpModel> getModels(Long companyId);

    Boolean bindPointAndModel(PointCompanyRelation relation);
}
