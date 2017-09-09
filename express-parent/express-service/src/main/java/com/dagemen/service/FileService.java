package com.dagemen.service;

import com.dagemen.dto.Kdniao.ElectronicSheetResponse;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 丁芙蓉 on 2017/8/20.
 */
public interface FileService {

    void viewFile(Long expNo, HttpServletResponse response);

    void getElectronicSheet(Long id, HttpServletResponse response);
}
