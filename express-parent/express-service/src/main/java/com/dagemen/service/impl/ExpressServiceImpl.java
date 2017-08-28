package com.dagemen.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dagemen.Utils.SessionHelper;
import com.dagemen.dao.ExpressMapper;
import com.dagemen.dto.ExpressSearchDTO;
import com.dagemen.entity.Express;
import com.dagemen.entity.Point;
import com.dagemen.service.ExpressService;
import org.springframework.beans.BeanUtils;
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
public class ExpressServiceImpl extends ServiceImpl<ExpressMapper, Express> implements ExpressService {


    @Override
    public Page getExpressList(Page page ,ExpressSearchDTO expressSearchDTO) {
        long pointId = ((Point)SessionHelper.getHttpSession().getAttribute("pointInfor")).getId();
        Express express = new Express();
        BeanUtils.copyProperties(expressSearchDTO, express);


        express.setExpCode(null);
        express.setPointId(pointId);
        // 0 表示查询所有
        if(expressSearchDTO.getCompanyId() == 0){
            express.setCompanyId(null);
        }
        EntityWrapper param = new EntityWrapper(express);
        if(expressSearchDTO.getStartDate() != null){
            param.ge("date", expressSearchDTO.getStartDate());
        }
        if(expressSearchDTO.getEndDate() != null){
            param.le("date", expressSearchDTO.getEndDate());
        }
        param.orderBy("date", false);
        param.like("exp_code", expressSearchDTO.getExpCode());
        return selectPage(page,param);
    }
}
