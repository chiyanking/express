package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dagemen.Utils.LoginSessionHelper;
import com.dagemen.Utils.SessionHelper;
import com.dagemen.dao.ExpressMapper;
import com.dagemen.dto.ExpressSearchDTO;
import com.dagemen.entity.Express;
import com.dagemen.entity.Point;
import com.dagemen.enums.ExpressStatusEnums;
import com.dagemen.enums.LabelValue;
import com.dagemen.exception.ApiException;
import com.dagemen.exception.ApiExceptionEnum;
import com.dagemen.service.ExpressService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
public class ExpressServiceImpl extends ServiceImpl<ExpressMapper, Express> implements ExpressService {


    @Override
    public boolean updateExpress(Express express) {
        if (express == null || express.getId() == null) {
            return false;
        }
        if (!express.getStatus().equals(ExpressStatusEnums.WAITE.getValue())) {
            throw new ApiException(ApiExceptionEnum.ONLY_CHANGE_NOT_PRINTE);
        }
        //门店修改信息不能修改状态
        express.setStatus(null);
        return insertOrUpdate(express);
    }

    @Override
    public Page getExpressList(Page page, ExpressSearchDTO expressSearchDTO) {
        long pointId = ((Point) SessionHelper.getHttpSession().getAttribute(LoginSessionHelper.loginform)).getId();
        Express express = new Express();
        BeanUtils.copyProperties(expressSearchDTO, express);


        express.setExpCode(null);
        express.setPointId(pointId);
        // 0 表示查询所有
        EntityWrapper param = new EntityWrapper(express);
        if (expressSearchDTO.getStartDate() != null) {
            param.gt("date", expressSearchDTO.getStartDate());
        }
        Date endDate = expressSearchDTO.getEndDate();
        if (endDate != null) {
            endDate = DateUtils.addDays(endDate, 1);
            param.lt("date", endDate);
        }
        if (expressSearchDTO.getExpCode() != null) {
            param.like("exp_code", expressSearchDTO.getExpCode());
        }
        String phone = expressSearchDTO.getPhone();
        if (StringUtils.isNotBlank(phone)) {
            param.like("sender_phone", phone).or("").like("receiver_phone", phone);
        }
        String name = expressSearchDTO.getName();
        if (StringUtils.isNotBlank(name)) {
            param.like("sender_name", name).or("").like("receiver_name", name);
        }
        param.orderBy("date", false);
        return selectPage(page, param);
    }

    @Override
    public boolean deleteById(Long expressId) {
        Express express = selectById(expressId);
        if (express == null) {
            throw new ApiException(ApiExceptionEnum.ExpressnotExistError);
        }
        if (!ExpressStatusEnums.WAITE.equals(express.getStatus())) {
            throw new ApiException(ApiExceptionEnum.ExpressStatusError);
        }
        express.setStatus(ExpressStatusEnums.Delete.getValue());
        return updateById(express);
    }

    @Override
    public List<LabelValue> expressStatus() {
        ArrayList<LabelValue> expressStatus = new ArrayList<>();
        for (ExpressStatusEnums expressStatusEnums : ExpressStatusEnums.values()) {
            LabelValue labelValue = new LabelValue();
            labelValue.setLabel(expressStatusEnums.getName());
            labelValue.setValue(expressStatusEnums.getValue());
            expressStatus.add(labelValue);
        }
        return expressStatus;
    }
}
