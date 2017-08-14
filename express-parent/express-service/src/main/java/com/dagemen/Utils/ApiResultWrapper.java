package com.dagemen.Utils;

import com.baomidou.mybatisplus.plugins.Page;
import com.dagemen.Utils.json.Jackson;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Copyright (C), 杭州典击科技有限公司
 * FileName: ApiResultWrapper
 * Author: Clark
 * Date: 2016/9/9 17:47
 * Description:
 */
public class ApiResultWrapper {

    public static String successMsg(String message) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("success", true);
        map.put("msg", message);
        return Jackson.mobile().writeValueAsString(map);
    }

    public static String successMsg() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("success", true);
        return Jackson.mobile().writeValueAsString(map);
    }

    public static String fail(String errorMsg) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("success", false);
        map.put("msg", errorMsg);
        return Jackson.mobile().writeValueAsString(map);
    }

    public static String failWithH5(String errorMsg, String callback) {
        return callback + "(" + fail(errorMsg) + ")";
    }

    /**
     * @param errorCode 错误码
     * @param errorMsg  错误提示
     */
    public static String fail(int errorCode, String errorMsg) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("success", false);
        map.put("code", errorCode);
        map.put("msg", errorMsg);
        return Jackson.mobile().writeValueAsString(map);
    }

    /**
     * <pre>
     * 接口层新规范,{success:false,data:{},msg:"xxx"}
     * 分页数据也要放data里
     * 警告：使用此方法必须和客户端协商，老版本APP是不支持此方法返回的格式的
     *
     * @param object
     * @return
     * @since 3.5.0
     */
    public static String successData(Object object) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("success", true);
        map.put("data", object);
        return Jackson.mobile().writeValueAsString(map);
    }

    /**
     * 当object为空对象时，需要传HashMap;当object为空数组时，传new ArrayList();
     * <br>
     * 注意：3.5.0以后定义新接口需要分页时请调用successData方法！老接口不能改。
     */
    public static String success(Object object) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("success", true);
        map.put("data", object);
        return Jackson.mobile().writeValueAsString(map);
    }

    public static String successWithH5(Map<String, Object> result, String callBack) {
        if (StringUtils.isNotBlank(callBack)) {
            // JOSNP
            return callBack + "(" + success(result) + ")";
        } else {
            return success(result);
        }
    }

    public static String successWithH5(Object result, String callBack) {
        if (StringUtils.isNotBlank(callBack)) {
            // JOSNP
            return callBack + "(" + success(result) + ")";
        } else {
            return success(result);
        }
    }

}