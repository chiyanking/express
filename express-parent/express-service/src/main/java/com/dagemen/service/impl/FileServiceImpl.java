package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.Utils.DateHelper;
import com.dagemen.service.kuaidiniao.CompanyCode;
import com.dagemen.service.kuaidiniao.KdGoldAPIDemo;
import com.dagemen.Utils.PdfUtil;
import com.dagemen.Utils.SessionHelper;
import com.dagemen.dto.Kdniao.*;
import com.dagemen.entity.ExpModel;
import com.dagemen.entity.Express;
import com.dagemen.entity.ExpressItem;
import com.dagemen.entity.PointCompanyRelation;
import com.dagemen.enums.ExpressStatusEnums;
import com.dagemen.exception.ApiException;
import com.dagemen.exception.ApiExceptionEnum;
import com.dagemen.service.*;
import net.sf.json.JSONObject;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Created by 丁芙蓉 on 2017/8/20.
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private ExpressService expressService;
    @Resource
    private ExpressItemService expressItemService;
    @Resource
    private ExpModelService expModelService;
    @Resource
    private PointCompanyRelationService pointCompanyRelationService;

    @Override
    public void viewFile(Long id, HttpServletResponse response) {

        Express express = new Express();
        express.setId(id);
        Express exp = expressService.selectOne(new EntityWrapper<>(express));
        if (exp == null) {
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

            if (!new File(outFilePath).exists()) {
                PdfUtil.creatPdf(jsonObject, filePath, outFilePath);
            }
            is = new FileInputStream(outFilePath);
            int nRead = 0;
            while ((nRead = is.read(buffer)) > 0) {
                response.getOutputStream().write(buffer, 0, nRead);
            }
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiException(ApiExceptionEnum.CREATE_EXP_MODEL_ERROR);
        } finally {
            try {
                is.close();
                response.getOutputStream().close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        exp.setStatus(ExpressStatusEnums.PRINTED.getValue());
        expressService.insertOrUpdate(exp);
    }

    @Override
    public ElectronicSheetResponse getElectronicSheet(Long id) {

        ElectronicSheetResponse responses = null;
        Express express = new Express();
        express.setId(id);
        Express exp = expressService.selectOne(new EntityWrapper<>(express));
        KdGoldAPIDemo kdGoldAPIDemo = new KdGoldAPIDemo();
        ElectronicSheetRequest esr = new ElectronicSheetRequest();

        esr.setOrderCode(exp.getTradeNo());//订单编号
        esr.setLogisticCode(exp.getExpNo());//快递单号

        PointCompanyRelation para = new PointCompanyRelation();
        para.setCompanyId(exp.getCompanyId());
        para.setPointId(exp.getPointId());
        PointCompanyRelation pointCompanyRelation = pointCompanyRelationService.selectOne(new EntityWrapper<>(para));
        esr.setCustomerName(pointCompanyRelation.getAccount());
        esr.setCustomerPwd(pointCompanyRelation.getPassword());
//            Map<String, String> maps = KdApiOrderDistinguish.getExpTraces(exp.getExpNo());
//            esr.setShipperCode(maps.get("ShipperCode"));//设置快递公司代码
        esr.setShipperCode(CompanyCode.getCompanyCode(exp.getCompanyName()));
        esr.setPayType(exp.getPayType());//邮费支付方式:1-现付，2-到付，3-月结，4-第三方支付
        esr.setExpType(1);//快递类型：1-标准快件
        esr.setCost(Optional.ofNullable(exp.getPrice()).orElse(BigDecimal.ZERO).doubleValue());//寄件费（运费）
//            esr.setOtherCost(1.0);//
        esr.setWeight(exp.getWeight());//寄件费（运费）
        esr.setQuantity(exp.getGoodsCount());//件数/包裹数
        esr.setVolume(exp.getVolume() == null ? 0 : Double.parseDouble(exp.getVolume()));//物品总体积m3
        esr.setRemark(Optional.ofNullable(exp.getRemark()).orElse("小心轻放！"));
        esr.setIsReturnPrintTemplate(1);

        Sender sender = new Sender();
        sender.setCompany(exp.getSenderCompany());
        sender.setAddress(exp.getSenderAddress());
        sender.setCityName(exp.getSenderCityName());
        sender.setMobile(exp.getSenderPhone());
        sender.setName(exp.getSenderName());
        sender.setProvinceName(exp.getSenderProvinceName());
        sender.setExpAreaName(exp.getSenderDistrictName());

        Receiver receiver = new Receiver();
        receiver.setCompany(exp.getReceiverCompany());
        receiver.setAddress(exp.getReceiverAddress());
        receiver.setCityName(exp.getReceiverCityName());
        receiver.setMobile(exp.getReceiverPhone());
        receiver.setName(exp.getReceiverName());
        receiver.setProvinceName(exp.getReceiverProvinceName());
        receiver.setExpAreaName(exp.getReceiverDistrictName());

        esr.setReceiver(receiver);
        esr.setSender(sender);

        ExpressItem param = new ExpressItem();
        para.setPointId(exp.getPointId());
        List<ExpressItem> expressItems = expressItemService.selectList(new EntityWrapper<>(param));
        for (ExpressItem item : Optional.ofNullable(expressItems).orElse(Collections.emptyList())) {
            Commodity commodity = new Commodity();
            commodity.setGoodsName(item.getItemName());
            commodity.setGoodsquantity(item.getItemNum());
            commodity.setGoodsWeight(item.getItemWight());
            esr.setCommodity(commodity);
        }
        if(expressItems.size()==0){
            Commodity commodity = new Commodity();
            commodity.setGoodsName("一般物品");
            commodity.setGoodsWeight(1d);
            commodity.setGoodsquantity(1);
            esr.setCommodity(commodity);
        }

        try {
            responses = kdGoldAPIDemo.orderOnlineByJson(esr);
        } catch (Exception e) {
            throw new ApiException(ApiExceptionEnum.ElectronicSheetError, e.getMessage());
        }
        if (Integer.parseInt(responses.getResultCode())!=100) {
            throw new ApiException("2000",responses.getReason());
        }
        PrintWriter writer = null;
        try {
            HttpServletResponse response = SessionHelper.getResponse();
            response.setContentType("text/html;charset=utf-8");
            writer = response.getWriter();
            writer.write(responses.getPrintTemplate());
        } catch (IOException e) {
            if (writer != null) {
                writer.close();
            }
        } finally {
            writer.close();
        }
        return null;
    }
}