package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.Utils.PdfUtil;
import com.dagemen.entity.ExpModel;
import com.dagemen.entity.Express;
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
import java.io.IOException;
import java.io.InputStream;

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
        ExpModel expMode1 = new ExpModel();
        expMode1.setId(exp.getExpModelId());
        ExpModel expMode = expModelService.selectOne(new EntityWrapper<>(expMode1));
        JSONObject jsonObject = JSONObject.fromObject(exp);
        String filePath = null;
        try {
            filePath = new ClassPathResource(expMode.getExpModelUrl()).getURL().getPath();
            byte[] bytes = PdfUtil.creatPdf(jsonObject, filePath);
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
            throw new ApiException(ApiExceptionEnum.CREATE_EXP_MODEL_ERROR);
        } finally {
            try{
                response.getOutputStream().close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
