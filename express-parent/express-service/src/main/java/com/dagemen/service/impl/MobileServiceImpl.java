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
import java.util.Date;
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
    public Express insertExpress(ExpressDTO expressDTO) {


        Express express = new Express();
        BeanUtils.copyProperties(expressDTO, express);
        //处理省市区
        List<Long> senderPCDCode = expressDTO.getSenderPCDCode();
        List<String> senderPCDName = expressDTO.getSenderPCDName();
        if(!CollectionUtils.isEmpty(senderPCDCode)){
            express.setSenderProvinceName(senderPCDName.get(0));
            express.setSenderProvinceCode(senderPCDCode.get(0));
            express.setSenderCityName(senderPCDName.get(1));
            express.setSenderCityCode(senderPCDCode.get(1));
            express.setSenderDistrictName(senderPCDName.get(2));
            express.setSenderDistrictCode(senderPCDCode.get(2));
        }
        List<Long> receiverPCDCode = expressDTO.getReceiverPCDCode();
        List<String> receiverPCDName = expressDTO.getReceiverPCDName();
        if(!CollectionUtils.isEmpty(receiverPCDCode)){
            express.setReceiverProvinceName(receiverPCDName.get(0));
            express.setReceiverProvinceCode(receiverPCDCode.get(0));
            express.setReceiverCityName(receiverPCDName.get(1));
            express.setReceiverCityCode(receiverPCDCode.get(1));
            express.setReceiverDistrictName(receiverPCDName.get(2));
            express.setReceiverDistrictCode(receiverPCDCode.get(2));
        }

//        List<Long> senderPCD = expressDTO.getSenderPCD();
//        if(!CollectionUtils.isEmpty(senderPCD)){
//            RegionProvince province = regionProvinceService.selectByCode(senderPCD.get(0));
//            if(province!=null){
//                express.setSenderProvinceCode(province.getCode());
//                express.setSenderProvinceId(province.getId());
//                express.setSenderProvinceName(province.getName());
//            }
//            RegionCity regionCity = regionCityService.selectByCode(senderPCD.get(1));
//            if(regionCity!=null){
//                express.setSenderCityCode(regionCity.getCode());
//                express.setSenderCityId(regionCity.getId());
//                express.setSenderCityName(regionCity.getName());
//            }
//            RegionDistrict regionDistrict=regionDistrictService.selectByCode(senderPCD.get(2));
//            if(regionDistrict!=null){
//                express.setSenderDistrictCode(regionDistrict.getCode());
//                express.setSenderDistrictId(regionDistrict.getId());
//                express.setSenderDistrictName(regionDistrict.getName());
//            }
//        }
//        List<Long> receiverPCD = expressDTO.getReceiverPCD();
//        if(!CollectionUtils.isEmpty(receiverPCD)){
//            RegionProvince province = regionProvinceService.selectByCode(receiverPCD.get(0));
//            if(province!=null){
//                express.setReceiverProvinceCode(province.getCode());
//                express.setReceiverProvinceId(province.getId());
//                express.setReceiverProvinceName(province.getName());
//            }
//            RegionCity regionCity = regionCityService.selectByCode(receiverPCD.get(1));
//            if(regionCity!=null){
//                express.setReceiverCityCode(regionCity.getCode());
//                express.setReceiverCityId(regionCity.getId());
//                express.setReceiverCityName(regionCity.getName());
//            }
//            RegionDistrict regionDistrict=regionDistrictService.selectByCode(receiverPCD.get(2));
//            if(regionDistrict!=null){
//                express.setReceiverDistrictCode(regionDistrict.getCode());
//                express.setReceiverDistrictId(regionDistrict.getId());
//                express.setReceiverDistrictName(regionDistrict.getName());
//            }
//        }



        User sender = new User();
        sender.setName(express.getSenderName());
        sender.setPhone(express.getSenderPhone());

        sender.setProvinceId(express.getSenderProvinceId());
        sender.setProvinceCode(express.getSenderProvinceCode());
        sender.setProvinceName(express.getSenderProvinceName());
        sender.setCityId(express.getSenderCityId());
        sender.setCityCode(express.getSenderCityCode());
        sender.setCityName(express.getSenderCityName());
        sender.setDistrictId(express.getSenderDistrictId());
        sender.setDistrictCode(express.getSenderDistrictCode());
        sender.setDistrictName(express.getSenderDistrictName());
        sender.setAddress(express.getSenderAddress());
        User oldSender = getUser(sender.getPhone());
        if (oldSender != null) {
            sender.setId(oldSender.getId());
            sender.setSendTimes(oldSender.getSendTimes()+1);
            sender.setLastSendDate(new Date());
        }
        userService.insertOrUpdate(sender);
        express.setSenderId(sender.getId());

        User receiver = new User();
        receiver.setAddress(express.getReceiverAddress());
        receiver.setName(express.getReceiverName());
        receiver.setPhone(express.getReceiverPhone());

        receiver.setProvinceId(express.getReceiverProvinceId());
        receiver.setProvinceCode(express.getReceiverProvinceCode());
        receiver.setProvinceName(express.getReceiverProvinceName());
        receiver.setCityId(express.getReceiverCityId());
        receiver.setCityCode(express.getReceiverCityCode());
        receiver.setCityName(express.getReceiverCityName());
        receiver.setDistrictId(express.getReceiverDistrictId());
        receiver.setDistrictCode(express.getReceiverDistrictCode());
        receiver.setDistrictName(express.getReceiverDistrictName());
        receiver.setAddress(express.getReceiverAddress());
        User oldReceiver = getUser(receiver.getPhone());
        if (oldReceiver != null) {
            receiver.setId(oldReceiver.getId());
            receiver.setReceiveTimes(oldReceiver.getReceiveTimes()+1);
            receiver.setLastReceiveDate(new Date());
        }
        userService.insertOrUpdate(receiver);
        express.setReceiverId(receiver.getId());
        expressService.insertOrUpdate(express);
        return express;
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
