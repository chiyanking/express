package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.Utils.DateHelper;
import com.dagemen.Utils.Kdniao.KdApiOrderDistinguish;
import com.dagemen.Utils.Kdniao.KdGoldAPIDemo;
import com.dagemen.Utils.PdfUtil;
import com.dagemen.dto.Kdniao.*;
import com.dagemen.entity.ExpModel;
import com.dagemen.entity.Express;
import com.dagemen.enums.ExpressStatusEnums;
import com.dagemen.exception.ApiException;
import com.dagemen.exception.ApiExceptionEnum;
import com.dagemen.service.ExpModelService;
import com.dagemen.service.ExpressService;
import com.dagemen.service.FileService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by 丁芙蓉 on 2017/8/20.
 */
@Service
public class FileServiceImpl implements FileService{

    @Autowired
    private ExpressService expressService;
    @Autowired
    private ExpModelService expModelService;

    @Override
    public void viewFile(Long id, HttpServletResponse response) {

        Express express = new Express();
        express.setId(id);
        Express exp = expressService.selectOne(new EntityWrapper<>(express));
        if(exp == null) {
            throw new ApiException(ApiExceptionEnum.ExpressnotExistError);
        }
        ExpModel expMode1 = new ExpModel();
        expMode1.setId(exp.getExpModelId());
        ExpModel expMode = expModelService.selectOne(new EntityWrapper<>(expMode1));
        JSONObject jsonObject = JSONObject.fromObject(exp);
        String filePath = null;
        byte[] buffer = new byte[256];
        InputStream is = null;
        try {
            filePath = new ClassPathResource(expMode.getExpModelUrl()).getURL().getPath();
            String outFilePath = filePath.substring(0, filePath.lastIndexOf("/")) + "/alreadyModel/" + exp.getSenderName() + "_" + DateHelper.getDateString(exp.getDate()) + ".pdf";

            if(!new File(outFilePath).exists()){
                PdfUtil.creatPdf(jsonObject, filePath, outFilePath);
            }
            is = new FileInputStream(outFilePath);
            int nRead = 0;
            while((nRead = is.read(buffer)) > 0){
                response.getOutputStream().write(buffer, 0, nRead);
            }
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiException(ApiExceptionEnum.CREATE_EXP_MODEL_ERROR);
        } finally {
            try{
                is.close();
                response.getOutputStream().close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        exp.setStatus(ExpressStatusEnums.PRINTED.getValue());
        expressService.insertOrUpdate(exp);
    }

    @Override
    public ElectronicSheetResponse getElectronicSheet(Long id) {

        ElectronicSheetResponse response = null;
        try {
            Express express = new Express();
            express.setId(id);
            Express exp = expressService.selectOne(new EntityWrapper<>(express));
            KdGoldAPIDemo kdGoldAPIDemo = new KdGoldAPIDemo();
            ElectronicSheetRequest esr = new ElectronicSheetRequest();

            esr.setOrderCode(exp.getExpCode());//快递单号

            KdApiOrderDistinguish kdApiOrderDistinguish = new KdApiOrderDistinguish();
            Map<String, String> maps = kdApiOrderDistinguish.getOrderTracesByJson(exp.getExpCode());
            esr.setShipperCode(maps.get("ShipperCode"));//设置快递公司代码
            esr.setPayType(exp.getPayStyle());//邮费支付方式:1-现付，2-到付，3-月结，4-第三方支付
            esr.setExpType(1);//快递类型：1-标准快件
            esr.setCost(exp.getPrice().doubleValue());//寄件费（运费）
//            esr.setOtherCost(1.0);//
            esr.setWeight(exp.getWeight());//寄件费（运费）
            esr.setQuantity(exp.getGoodsCount());//件数/包裹数
//            esr.setVolume(0.0);//物品总体积m3
            esr.setRemark("小心轻放");
            esr.setIsReturnPrintTemplate(1);

            Sender sender = new Sender();
//            sender.setCompany("LV");
            sender.setAddress(exp.getSenderAddress());
            sender.setCityName(exp.getSenderCityName());
            sender.setMobile(exp.getSenderPhone());
            sender.setName(exp.getSenderName());
            sender.setProvinceName(exp.getSenderProvinceName());
            sender.setExpAreaName(exp.getSenderDistrictName());

            Receiver receiver = new Receiver();
//            receiver.setCompany("LV");
            receiver.setAddress(exp.getReceiverAddress());
            receiver.setCityName(exp.getReceiverCityName());
            receiver.setMobile(exp.getReceiverPhone());
            receiver.setName(exp.getReceiverName());
            receiver.setProvinceName(exp.getReceiverProvinceName());
            receiver.setExpAreaName(exp.getReceiverDistrictName());

            esr.setReceiver(receiver);
            esr.setSender(sender);

            Commodity commodity = new Commodity();
            commodity.setGoodsName("物品");
            commodity.setGoodsquantity(1);
            commodity.setGoodsWeight(1.0);
            esr.setCommodity(commodity);

            response = kdGoldAPIDemo.orderOnlineByJson(esr);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException(ApiExceptionEnum.CREATE_EXP_MODEL_ERROR);
        }
        return response;
    }
}
