package com.dagemen.Utils;

import com.baomidou.mybatisplus.plugins.Page;
import com.dagemen.Utils.json.Jackson;
import com.dagemen.exception.ApiExceptionEnum;
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

    public static Map<String, Object> success(Object data) {
        Map<String, Object> map = new LinkedHashMap();
        map.put("errCode", 0);
        if (data instanceof Boolean) {
            map.put("msg", "成功");
        } else if (data instanceof String) {
            map.put("msg", data);
        } else {
            map.put("data", data);
        }
        return map;
    }

    public static Map<String, Object> fail(ApiExceptionEnum enums) {
        Map<String, Object> map = new LinkedHashMap();
        map.put("errCode", enums.getCode());
        map.put("msg", enums.getMessage());
        return map;
    }

    public static Map<String, Object> success() {
        Map<String, Object> map = new LinkedHashMap();
        map.put("errCode", 0);
        map.put("msg", "成功");
        return map;
    }
}