package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.dto.ExpressDTO;
import com.dagemen.dto.PointAddressDto;
import com.dagemen.entity.*;
import com.dagemen.exception.ApiException;
import com.dagemen.exception.ApiExceptionEnum;
import com.dagemen.service.*;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MobileServiceImpl implements MobileService {

    @Resource
    UserService userService;
    @Resource
    ExpressService expressService;
    @Resource
    PointService pointService;
    @Resource
    CompanyService companyService;
    @Resource
    PointCompanyRelationService pointCompanyRelationService;

    @Override
    public boolean insertExpress(ExpressDTO expressDTO) {

        Express express = new Express();
        BeanUtils.copyProperties(expressDTO, express);

        User sender = new User();
        sender.setName(express.getSenderName());
        sender.setPhone(express.getSenderPhone());

        sender.setProvinceId(express.getSenderProvinceId());
        sender.setCityId(express.getSenderCityId());
        sender.setDistrictId(express.getReceiverDistrictId());
        sender.setAddress(express.getSenderAddress());
        User oldSender = getUser(sender.getPhone());
        if (oldSender == null) {
            sender.setPhone(oldSender.getPhone());
        }
        userService.insertOrUpdate(sender);
        express.setSenderId(sender.getId());

        User receiver = new User();
        receiver.setAddress(expressDTO.getReceiverAddress());
        receiver.setName(expressDTO.getReceiverName());
        receiver.setPhone(expressDTO.getReceiverPhone());
        receiver.setProvinceId(expressDTO.getReceiverProvinceId());
        receiver.setCityId(expressDTO.getReceiverCityId());
        receiver.setDistrictId(expressDTO.getReceiverDistrictId());
        receiver.setAddress(expressDTO.getReceiverAddress());
        User oldReceiver = getUser(receiver.getPhone());
        if (oldReceiver == null) {
            receiver.setId(oldReceiver.getId());
        }
        userService.insertOrUpdate(receiver);
        express.setReceiverId(receiver.getId());

        return expressService.insertOrUpdate(express);
    }

    @Override
    public PointAddressDto getPointAddress(long pointId) {

        Point params = new Point();
        params.setId(pointId);
        Point point = pointService.selectOne(new EntityWrapper<>(params));
        PointCompanyRelation pointCompanyRelationParams = new PointCompanyRelation();
        pointCompanyRelationParams.setPointId(pointId);
        List<PointCompanyRelation> pointCompanyRelationList = pointCompanyRelationService.selectList(new EntityWrapper<>(pointCompanyRelationParams));
        if(pointCompanyRelationList == null || pointCompanyRelationList.size()<=0){
            throw new ApiException(ApiExceptionEnum.NO_SELECTE_COMPANY);
        }
        List<Long> companyIds = new ArrayList<>();
        for(PointCompanyRelation obj : pointCompanyRelationList){
            companyIds.add(obj.getExpressId());
        }
        List<Company> companyList = companyService.selectList(new EntityWrapper<>(new Company()).in("id",companyIds));
        PointAddressDto pointAddressDto = new PointAddressDto();
        BeanUtils.copyProperties(point, pointAddressDto);
        pointAddressDto.setCompanies(companyList);
        return pointAddressDto;
    }

    private User getUser(String phone) {
        User user = new User();
        user.setPhone(phone);
        return userService.selectOne(new EntityWrapper<>(user));
    }


}
