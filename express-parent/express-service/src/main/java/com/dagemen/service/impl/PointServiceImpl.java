package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.Utils.EncryptAndDecryptUtil;
import com.dagemen.Utils.LoginSessionHelper;
import com.dagemen.Utils.SessionHelper;
import com.dagemen.dao.PointMapper;
import com.dagemen.dto.PointUpdateCompanyDTO;
import com.dagemen.entity.Company;
import com.dagemen.entity.Point;
import com.dagemen.entity.PointCompanyRelation;
import com.dagemen.enums.LabelValue;
import com.dagemen.exception.ApiException;
import com.dagemen.exception.ApiExceptionEnum;
import com.dagemen.service.CompanyService;
import com.dagemen.service.PointCompanyRelationService;
import com.dagemen.service.PointService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author dagemen
 * @since 2017-08-19
 */
@Service
@Transactional
public class PointServiceImpl extends ServiceImpl<PointMapper, Point> implements PointService {

    @Resource
    PointCompanyRelationService pointCompanyRelationService;

    @Resource
    CompanyService companyService;

    @Resource
    PointCompanyRelationService relationService;

    @Override
    public boolean checkLogin(Point point, HttpSession httpSession) {
        Point param = new Point();
        param.setPhone(point.getPhone());
        param.setPassword(EncryptAndDecryptUtil.encryptByMD5(point.getPassword()));
        Point point1 = selectOne(new EntityWrapper<>(param));
        if (point1 == null) {
            throw new ApiException(ApiExceptionEnum.USER_LOGIN_ERROR);
        }
        LoginSessionHelper.logIn(point1, null, httpSession);
        return true;
    }

    @Override
    public boolean logOut(HttpSession httpSession) {
        return false;
    }

    @Override
    public boolean addPointRelationCompanies(List<PointUpdateCompanyDTO> params) {

        PointCompanyRelation pointCompanyRelation = new PointCompanyRelation();
        pointCompanyRelation.setPointId(params.get(0).getPointId());
        pointCompanyRelationService.delete(new EntityWrapper<>(pointCompanyRelation));
        List<PointCompanyRelation> list = new ArrayList<>();
        PointCompanyRelation pcr = new PointCompanyRelation();
        for (PointUpdateCompanyDTO obj : params) {
            BeanUtils.copyProperties(obj, pcr);
            list.add(pcr);
        }
        pointCompanyRelationService.insertBatch(list);
        return false;
    }

    @Override
    public List<LabelValue> getAllCompanies() {
        EntityWrapper<Company> param = new EntityWrapper<>(new Company());
        List<Company> companies = companyService.selectList(param);
        ArrayList<LabelValue> labelValues = new ArrayList<>();
        if(CollectionUtils.isEmpty(companies)){
            return labelValues;
        }
        companies.forEach((item)->{
            LabelValue labelValue = new LabelValue();
            labelValue.setLabel(item.getName());
            labelValue.setValue(item.getId());
        });
        return labelValues ;
    }

    @Override
    public List<LabelValue> getHasCompany() {
        ArrayList<LabelValue> labelValues = new ArrayList<>();
        PointCompanyRelation param = new PointCompanyRelation();
        Long pointId = ((Point) SessionHelper.getHttpSession().getAttribute(LoginSessionHelper.loginform)).getId();
        param.setPointId(pointId);
        EntityWrapper<PointCompanyRelation> entityParam = new EntityWrapper<>(param);
        List<PointCompanyRelation> pointCompanyRelations = relationService.selectList(entityParam);
        if(CollectionUtils.isEmpty(pointCompanyRelations)){
            return labelValues;
        }
        List<Long> companyIds=new ArrayList<>();
        pointCompanyRelations.forEach((item)->{
            companyIds.add(item.getCompanyId());
        });
        EntityWrapper<Company> par = new EntityWrapper<>();
        par.in("id",companyIds);
        List<Company> companies = companyService.selectList(par);
        if(CollectionUtils.isEmpty(companyIds)){
            return labelValues;
        }
        companies.forEach((item)->{
            LabelValue labelValue = new LabelValue();
            labelValue.setValue(item.getId());
            labelValue.setLabel(item.getName());
            labelValues.add(labelValue);
        });

        return labelValues;
    }
}
