package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.toolkit.TableInfoHelper;
import com.dagemen.Utils.SessionHelper;
import com.dagemen.dao.ExpressMapper;
import com.dagemen.dto.ExpressSearchDTO;
import com.dagemen.dto.ExpressParam;
import com.dagemen.entity.Company;
import com.dagemen.entity.Express;
import com.dagemen.entity.ExpressItem;
import com.dagemen.enums.ExpressStatusEnums;
import com.dagemen.enums.LabelValue;
import com.dagemen.exception.ApiException;
import com.dagemen.exception.ApiExceptionEnum;
import com.dagemen.service.CompanyService;
import com.dagemen.service.ExpressItemService;
import com.dagemen.service.ExpressService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.text.html.Option;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
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
public class ExpressServiceImpl extends ServiceImpl<ExpressMapper, Express> implements ExpressService {


    @Resource
    private ExpressItemService expressItemService;
    @Resource
    private CompanyService companyService;

    @Override
    public boolean updateExpress(ExpressParam exp) {
        if (exp == null || exp.getId() == null) {
            return false;
        }
//        if (!express.getStatus().equals(ExpressStatusEnums.WAITE.getValue())) {
//            throw new ApiException(ApiExceptionEnum.ONLY_CHANGE_NOT_PRINTE);
//        }
        //门店修改信息不能修改状态
        Express express = new Express();
        BeanUtils.copyProperties(exp, express);

        Long companyId = express.getCompanyId();
        if (companyId != null) {
            Company company = companyService.selectById(companyId);
            express.setCompanyName(Optional.ofNullable(company).map(val -> val.getName()).orElse(null));
        }

        ExpressItem item = new ExpressItem();
        item.setExpId(express.getId());
        ExpressItem expressItem = expressItemService.selectOne(new EntityWrapper<>(item));

        if (expressItem != null) {
            expressItem.setItemWight(exp.getWeight());
            expressItemService.updateById(expressItem);
        }

        return insertOrUpdate(express);
    }

    @Override
    public Page getExpressList(Page page, ExpressSearchDTO expressSearchDTO) {
        long pointId = Optional.ofNullable(SessionHelper.getLoginPoint()).map(point -> point.getId()).orElse(null);
        Express express = new Express();
        BeanUtils.copyProperties(expressSearchDTO, express);


        express.setExpNo(null);
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
        if (expressSearchDTO.getExpNo() != null) {
            param.like("exp_no", expressSearchDTO.getExpNo());
        }
        String phone = expressSearchDTO.getPhone();
        if (StringUtils.isNotBlank(phone)) {
            param.like("sender_phone", phone).or("").like("receiver_phone", phone);
        }
        String name = expressSearchDTO.getName();
        if (StringUtils.isNotBlank(name)) {
            param.like("sender_name", name).or("").like("receiver_name", name);
        }

        //不查询出来某个字段
        StringBuilder sb = new StringBuilder();
        for (Field field : TableInfoHelper.getAllFields(Express.class)) {
            if (!"templateHtml".equals(field.getName()))
                sb.append(com.baomidou.mybatisplus.toolkit.StringUtils.camelToUnderline(field.getName()))
                        .append(" as ")
                        .append(field.getName())
                        .append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        param.setSqlSelect(sb.toString());
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
        express.setStatus(ExpressStatusEnums.DELETE.getValue());
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
