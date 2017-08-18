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

    public static Map<String, Object> success(String message) {
        Map<String, Object> map = new LinkedHashMap();
        map.put("success", true);
        map.put("message", message);
        return map;
    }

    public static Map<String, Object> success(Object data) {
        Map<String, Object> map = new LinkedHashMap();
        map.put("success", true);
        map.put("data", data);
        return map;
    }

    public static Map<String, Object> fail(String errorMsg) {
        Map<String, Object> map = new LinkedHashMap();
        map.put("success", false);
        map.put("message", errorMsg);
        return map;
    }

//    public static Map<String, Object> fail(BaseResultCode baseResultCode) {
//        Map<String, Object> map = new LinkedHashMap();
//        map.put("success", false);
//        map.put("code", baseResultCode.getCode());
//        map.put("message", baseResultCode.getMessage());
//        return map;
//    }
}