package com.dagemen.service;

import com.dagemen.dto.Kdniao.ElectronicSheetResponse;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 丁芙蓉 on 2017/8/20.
 */
public interface FileService {

    void viewFile(Long expNo, HttpServletResponse response);

    /**
     * 向前台打印电子面单
     * @param id               快递单Id
     * @param isCreateNewSheet 是否重新调用第三方接口  如果重复调用可能会产生费用
     * @return  返回电子面单Html
     */
    String getElectronicSheet(Long id, Boolean isCreateNewSheet);

    /**
     *  输出到电子面单上面
     * @param electronicHtml  电子面单html
     * @return
     */
    boolean writeToResponse(String electronicHtml);
}
