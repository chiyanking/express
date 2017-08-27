package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.dto.ExpressDTO;
import com.dagemen.entity.Express;
import com.dagemen.entity.User;
import com.dagemen.service.ExpressService;
import com.dagemen.service.MobileService;
import com.dagemen.service.UserService;
import org.springframework.beans.BeanUtils;
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

    private User getUser(String phone) {
        User user = new User();
        user.setPhone(phone);
        return userService.selectOne(new EntityWrapper<>(user));
    }


}
