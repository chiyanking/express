package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.Utils.EncryptAndDecryptUtil;
import com.dagemen.Utils.LoginSessionHelper;
import com.dagemen.dao.PointMapper;
import com.dagemen.dto.PointUpdateCompanyDTO;
import com.dagemen.entity.Company;
import com.dagemen.entity.Point;
import com.dagemen.entity.PointCompanyRelation;
import com.dagemen.exception.ApiException;
import com.dagemen.exception.ApiExceptionEnum;
import com.dagemen.service.PointCompanyRelationService;
import com.dagemen.service.PointService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
    public boolean addPointRelationCompanys(List<PointUpdateCompanyDTO> params) {

        PointCompanyRelation pointCompanyRelation = new PointCompanyRelation();
        pointCompanyRelation.setPointId(params.get(0).getPointId());
        pointCompanyRelationService.delete(new EntityWrapper<>(pointCompanyRelation));
        List<PointCompanyRelation> list = new ArrayList<>();
        PointCompanyRelation pcr = new PointCompanyRelation();
        for(PointUpdateCompanyDTO obj : params){
            BeanUtils.copyProperties(obj, pcr);
            list.add(pcr);
        }
        pointCompanyRelationService.insertBatch(list);
        return false;
    }
}
