package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.Utils.SessionHelper;
import com.dagemen.dao.CompanyMapper;
import com.dagemen.entity.Company;
import com.dagemen.entity.ExpModel;
import com.dagemen.entity.Point;
import com.dagemen.entity.PointCompanyRelation;
import com.dagemen.enums.ExpModelStatusEnum;
import com.dagemen.enums.LabelValue;
import com.dagemen.exception.ApiException;
import com.dagemen.exception.ApiExceptionEnum;
import com.dagemen.service.CompanyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dagemen.service.ExpModelService;
import com.dagemen.service.PointCompanyRelationService;
import com.dagemen.service.UserService;
import org.apache.ibatis.parsing.TokenHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dagemen
 * @since 2017-08-19
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

    @Resource
    PointCompanyRelationService pointCompanyRelationService;

    @Resource
    CompanyService companyService;
    @Resource
    ExpModelService expModelService;

    @Override
    public List<LabelValue> getAllCompanies() {
        List<LabelValue> comOptions = new ArrayList<>();
        List<Company> companies = selectList(new EntityWrapper<>());
        for (Company company : Optional.ofNullable(companies).orElse(Collections.emptyList())) {
            LabelValue labelValue = new LabelValue();
            labelValue.setLabel(company.getName());
            labelValue.setValue(company.getId());
            comOptions.add(labelValue);
        }
        return comOptions;
    }

    @Override
    public List<PointCompanyRelation> getHasCompanies() {
        Long pointId = SessionHelper.getLoginPoint().getId();
        PointCompanyRelation relation = new PointCompanyRelation();
        relation.setPointId(pointId);
        return pointCompanyRelationService.selectList(new EntityWrapper<>(relation));
    }

    @Override
    public List<ExpModel> getModels(Long companyId) {
        Optional.ofNullable(companyId).orElseThrow(() -> new ApiException(ApiExceptionEnum.PARAM_ERROR));
        ExpModel expModel = new ExpModel();
        expModel.setCompanyId(companyId);
        expModel.setExpModelStatus(ExpModelStatusEnum.Vaild.getValue());
        return expModelService.selectList(new EntityWrapper<>(expModel));
    }

    @Override
    public Boolean bindPointAndModel(PointCompanyRelation relation) {
        if (relation == null) {
            return false;
        }
        Point point = SessionHelper.getLoginPoint();
        relation.setPointId(point.getId());
        relation.setPointName(point.getName());

        Long companyId = relation.getCompanyId();

        PointCompanyRelation param = new PointCompanyRelation();
        param.setPointId(point.getId());
        param.setCompanyId(companyId);
        EntityWrapper<PointCompanyRelation> wrapper = new EntityWrapper<>(param);
        if (relation.getId() != null) {
            wrapper.ne("id", relation.getId());
        }
        int r = pointCompanyRelationService.selectCount(wrapper);
        if (r > 0) {
            throw new ApiException(ApiExceptionEnum.POINT_COMPANY_ERROR);
        }

        if (companyId != null) {
            Company company = companyService.selectById(companyId);
            relation.setCompanyName(company.getName());
        }
        Long expModelId = relation.getExpModelId();
        if (expModelId != null) {
            ExpModel expModel = expModelService.selectById(expModelId);
            relation.setExpModelName(expModel.getExpModelName());
        }

        return pointCompanyRelationService.insertOrUpdate(relation);
    }
}
