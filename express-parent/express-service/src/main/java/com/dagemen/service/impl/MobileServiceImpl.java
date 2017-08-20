package com.dagemen.service.impl;

import com.dagemen.dto.ExpressDTO;
import com.dagemen.entity.User;
import com.dagemen.service.ExpressService;
import com.dagemen.service.MobileService;
import com.dagemen.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class MobileServiceImpl implements MobileService {

    @Resource
    UserService userService;

    @Resource
    ExpressService expressService;

    @Override
    public boolean insertExpress(ExpressDTO expressDTO) {

        User reciver = new User();
        reciver.setAddress(expressDTO.getReceiverAddress());
        reciver.setName(expressDTO.getReceiverName());
        reciver.setPhone(expressDTO.getReceiverPhone());
        reciver.setProvinceId(expressDTO.getReceiverProvinceId());
        reciver.setCityId(expressDTO.getReceiverCityId());
        reciver.setDistrictId(expressDTO.getReceiverDistrictId());
        reciver.setAddress(expressDTO.getReceiverAddress());

        return false;
    }


}
