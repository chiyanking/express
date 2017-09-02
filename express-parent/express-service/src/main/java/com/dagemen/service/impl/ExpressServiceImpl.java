package com.dagemen.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ExpressServiceImpl extends ServiceImpl<ExpressMapper, Express> implements ExpressService {


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
            param.ge("date", expressSearchDTO.getStartDate());
        }
        if (expressSearchDTO.getEndDate() != null) {
            param.le("date", expressSearchDTO.getEndDate());
        }
        if (expressSearchDTO.getExpCode() != null) {
            param.like("exp_code", expressSearchDTO.getExpCode());
        }
        String phone = expressSearchDTO.getPhone();
        if (StringUtils.isNotBlank(phone)) {
            param.like("sender_phone", phone).or("").like("receiver_phone", phone);
        }
        String name = expressSearchDTO.getName();
        if(StringUtils.isNotBlank(name)){
            param.like("sender_name",name).or("").like("receiver_name",name);
        }
        param.orderBy("date", false);
        return selectPage(page, param);
    }

    @Override
    public boolean deleteById(Long expressId) {
        Express express = selectById(expressId);
        if (ExpressStatusEnums.PRINTED.equals(express.getStatus())) {
            new ApiException(ApiExceptionEnum.ExpressStatusError);
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
