package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.IdWorker;
import com.dagemen.Utils.DateHelper;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
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
    @Resource
    private CompanyService companyService;

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
        byte[] buffer = new byte[256];
        InputStream is = null;
        try {
            String filePath = new ClassPathResource(expMode.getExpModelUrl()).getURL().getPath();
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
    public String getElectronicSheet(Long id, Boolean isCreateNewSheet) {
        Express exp = expressService.selectById(id);
        ElectronicSheetRequest formData = new ElectronicSheetRequest();
        //重新生成 交易单号
        String tradeNo = isCreateNewSheet ? exp.getTradeNo() : String.valueOf(IdWorker.getId());
        formData.setOrderCode(tradeNo);//订单编号
        formData.setLogisticCode(exp.getExpNo());//快递单号

        PointCompanyRelation para = new PointCompanyRelation();
        para.setCompanyId(exp.getCompanyId());
        para.setPointId(exp.getPointId());
        PointCompanyRelation pointCompanyRelation = pointCompanyRelationService.selectOne(new EntityWrapper<>(para));
        //快递公司编号  快递公司 账号密码
        formData.setCustomerName(pointCompanyRelation.getAccount());
        formData.setCustomerPwd(pointCompanyRelation.getPassword());
        formData.setShipperCode(Optional.ofNullable(companyService.selectById(exp.getCompanyId())).map((val) -> val.getCode()).orElse(null));

        formData.setPayType(exp.getPayType());//邮费支付方式:1-现付，2-到付，3-月结，4-第三方支付
        formData.setExpType(1);//快递类型：1-标准快件
        formData.setCost(Optional.ofNullable(exp.getPrice()).orElse(BigDecimal.ZERO).doubleValue());//寄件费（运费）
//            esr.setOtherCost(1.0);//
        formData.setWeight(exp.getWeight());//寄件费（运费）
        formData.setQuantity(exp.getGoodsCount());//件数/包裹数
        formData.setVolume(exp.getVolume() == null ? 0 : Double.parseDouble(exp.getVolume()));//物品总体积m3
        formData.setRemark(Optional.ofNullable(exp.getRemark()).orElse("小心轻放！"));
        formData.setIsReturnPrintTemplate(1);

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

        formData.setReceiver(receiver);
        formData.setSender(sender);

        ExpressItem param = new ExpressItem();
        param.setExpId(id);
        List<ExpressItem> expressItems = expressItemService.selectList(new EntityWrapper<>(param));
        for (ExpressItem item : expressItems) {
            Commodity commodity = new Commodity();
            commodity.setGoodsName(item.getItemName());
            commodity.setGoodsquantity(item.getItemNum());
            commodity.setGoodsWeight(item.getItemWight());
            formData.setCommodity(commodity);
        }
        if (CollectionUtils.isEmpty(expressItems)) {
            Commodity commodity = new Commodity();
            commodity.setGoodsName("一般物品");
            commodity.setGoodsWeight(exp.getWeight());
            commodity.setGoodsquantity(exp.getGoodsCount());
            formData.setCommodity(commodity);
        }
        if (!isCreateNewSheet && StringUtils.isNotBlank(exp.getTemplateHtml())) {
            return exp.getTemplateHtml();
        }


        ElectronicSheetResponse responses = sendThirdPartAPI(formData);
        Order order = responses.getOrder();
        String logisticCode = order.getLogisticCode();
        exp.setTradeNo(tradeNo);
        exp.setExpNo(logisticCode);
        exp.setTemplateHtml(responses.getPrintTemplate());
        expressService.insertOrUpdate(exp);
        return exp.getTemplateHtml();
    }


    public final static KdGoldAPIDemo kdGoldAPIDemo = new KdGoldAPIDemo();

    private ElectronicSheetResponse sendThirdPartAPI(ElectronicSheetRequest electronicSheet) {
        try {
            ElectronicSheetResponse electronicSheetResponse = kdGoldAPIDemo.orderOnlineByJson(electronicSheet);
            if (!new Integer(100).equals(Integer.parseInt(electronicSheetResponse.getResultCode()))) {
                throw new ApiException("2000", electronicSheetResponse.getReason());
            }
            return electronicSheetResponse;
        } catch (Exception e) {
            throw new ApiException(ApiExceptionEnum.ElectronicSheetError, e.getMessage());
        }
    }


    public boolean writeToResponse(String electronicHtml) {
        PrintWriter writer = null;
        try {
            HttpServletResponse response = SessionHelper.getResponse();
            response.setContentType("text/html;charset=utf-8");
            writer = response.getWriter();
            writer.write(electronicHtml);
        } catch (IOException e) {
            if (writer != null) {
                writer.close();
            }
        } finally {
            writer.close();
        }
        return true;
    }
}