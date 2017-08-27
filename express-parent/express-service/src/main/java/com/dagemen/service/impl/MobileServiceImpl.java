package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.dto.ExpressDTO;
import com.dagemen.dto.PointAddressDto;
import com.dagemen.entity.*;
import com.dagemen.exception.ApiException;
import com.dagemen.exception.ApiExceptionEnum;
import com.dagemen.service.*;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.util.CollectionUtils;
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
    RegionProvinceService regionProvinceService;
    @Resource
    RegionCityService regionCityService;
    @Resource
    RegionDistrictService regionDistrictService;


    @Resource
    CompanyService companyService;
    @Resource
    PointCompanyRelationService pointCompanyRelationService;

    @Override
    public boolean insertExpress(ExpressDTO expressDTO) {

        Express express = new Express();
        BeanUtils.copyProperties(expressDTO, express);
        //处理省市区
        List<Long> senderPCD = expressDTO.getSenderPCD();
        if(!CollectionUtils.isEmpty(senderPCD)){
            RegionProvince province = regionProvinceService.selectByCode(senderPCD.get(0));
            if(province!=null){
                express.setSenderProvinceCode(province.getCode());
                express.setSenderProvinceId(province.getId());
                express.setSenderProvinceName(province.getName());
            }
            RegionCity regionCity = regionCityService.selectByCode(senderPCD.get(1));
            if(regionCity!=null){
                express.setSenderCityCode(regionCity.getCode());
                express.setSenderCityId(regionCity.getId());
                express.setSenderCityName(regionCity.getName());
            }
            RegionDistrict regionDistrict=regionDistrictService.selectByCode(senderPCD.get(2));
            if(regionDistrict!=null){
                express.setSenderDistrictCode(regionDistrict.getCode());
                express.setSenderDistrictId(regionDistrict.getId());
                express.setSenderDistrictName(regionDistrict.getName());
            }
        }
        List<Long> receiverPCD = expressDTO.getReceiverPCD();
        if(!CollectionUtils.isEmpty(receiverPCD)){
            RegionProvince province = regionProvinceService.selectByCode(receiverPCD.get(0));
            if(province!=null){
                express.setReceiverProvinceCode(province.getCode());
                express.setReceiverProvinceId(province.getId());
                express.setReceiverProvinceName(province.getName());
            }
            RegionCity regionCity = regionCityService.selectByCode(receiverPCD.get(1));
            if(regionCity!=null){
                express.setReceiverCityCode(regionCity.getCode());
                express.setReceiverCityId(regionCity.getId());
                express.setReceiverCityName(regionCity.getName());
            }
            RegionDistrict regionDistrict=regionDistrictService.selectByCode(receiverPCD.get(2));
            if(regionDistrict!=null){
                express.setReceiverDistrictCode(regionDistrict.getCode());
                express.setReceiverDistrictId(regionDistrict.getId());
                express.setReceiverDistrictName(regionDistrict.getName());
            }
        }

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
        receiver.setAddress(express.getReceiverAddress());
        receiver.setName(express.getReceiverName());
        receiver.setPhone(express.getReceiverPhone());

        receiver.setProvinceId(express.getReceiverProvinceId());
        receiver.setCityId(express.getReceiverCityId());
        receiver.setDistrictId(express.getReceiverDistrictId());
        receiver.setAddress(express.getReceiverAddress());
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
        if(CollectionUtils.isEmpty(pointCompanyRelationList)){
            throw new ApiException(ApiExceptionEnum.NO_SELECTE_COMPANY);
        }
        List<Long> companyIds = new ArrayList<>();
        for(PointCompanyRelation obj : pointCompanyRelationList){
            companyIds.add(obj.getCompanyId());
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
