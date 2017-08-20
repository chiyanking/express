package com.dagemen.service.impl;

import com.dagemen.Utils.EncryptAndDecryptUtil;
import com.dagemen.Utils.LoginSessionHelper;
import com.dagemen.dao.PointMapper;
import com.dagemen.entity.Point;
import com.dagemen.exception.ApiException;
import com.dagemen.exception.ApiExceptionEnum;
import com.dagemen.service.PointService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dagemen
 * @since 2017-08-19
 */
@Service
public class PointServiceImpl extends ServiceImpl<PointMapper, Point> implements PointService {

    @Autowired
    PointMapper pointMapper;

    @Override
    public boolean checkLogin(Point point, HttpSession httpSession) {
        Point parms = new Point();
        parms.setPhone(point.getPhone());
        parms.setPassword(EncryptAndDecryptUtil.encryptByMD5(point.getPassword()));
        Point point1 = pointMapper.selectOne(point);
        if(point1 == null){
            throw new ApiException(ApiExceptionEnum.USER_LOGIN_ERROR);
        }
        LoginSessionHelper.logIn(point1, null, httpSession);
        return true;
    }

    @Override
    public boolean logOut(HttpSession httpSession) {
        return false;
    }
}
