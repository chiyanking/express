package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.Utils.PdfUtil;
import com.dagemen.entity.ExpModel;
import com.dagemen.entity.Express;
import com.dagemen.service.ExpModelService;
import com.dagemen.service.ExpressService;
import com.dagemen.service.FileService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        byte[] bytes = PdfUtil.creatPdf(jsonObject, expMode.getExpModelUrl());
        try {
            int nRead = 0;
            response.getOutputStream().write(bytes, 0, nRead);
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                response.getOutputStream().close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
