package com.dagemen.Utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dagemen.dto.AcceptDTO;
import com.dagemen.model.kdniao.KdniaoModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 丁芙蓉 on 2017/8/20.
 */
public class SearchParamsHelper {

    /**
     * 要模糊查找的字段
     */
    private static final String[] FUZZY_SEARCH = {"HLA", "Mut_peptide", "clientNo", "protein_homologous_detail"};



    /**
     * 传入的参数，根据不同的类型放入不同的map
     * @param searchParam
     * @return
     */
    public static Map<String, List<Map<String, Object>>> getParamsMaps(String searchParam) {
        Map<String, List<Map<String, Object>>> maps = new HashedMap();
        List<Map<String, Object>> stringList = new ArrayList<>();
        List<Map<String, Object>> integerList = new ArrayList<>();

        if(StringUtils.isNotBlank(searchParam)){
            Gson gson = new Gson();
            List<AcceptDTO> list = gson.fromJson(searchParam, new TypeToken<Map<String, AcceptDTO>>() {}.getType());
            for(AcceptDTO acceptDTO : list){
                Map<String, Object> map = new HashedMap();
                if(isContains(acceptDTO.getKey(), FUZZY_SEARCH)){
                    map.put("key", acceptDTO.getKey());
                    map.put("value", acceptDTO.getValue()==null?null:acceptDTO.getValue().trim());
                    stringList.add(map);
                }else {
                    map.put("key", acceptDTO.getKey());
                    map.put("value", acceptDTO.getValue()==null?null:acceptDTO.getValue().trim());
                    integerList.add(map);
                }
            }
        }
        maps.put("stringList", stringList);
        maps.put("integerList", integerList);
        return maps;
    }

    /**
     * 解析范围查找的参数
     * @param searchParam
     * @return
     */
    public static List<Map<String, Object>> getRangeParamsMaps(String searchParam) {
        List<Map<String, Object>> maps = new ArrayList<>();
        if(StringUtils.isNotBlank(searchParam)){
            Gson gson = new Gson();
            List<AcceptDTO> list = gson.fromJson(searchParam, new TypeToken<Map<String, AcceptDTO>>() {}.getType());
            for(AcceptDTO acceptDTO : list){
                Map<String, Object> map = new HashedMap();
                map.put("key", acceptDTO.getKey());
                map.put("maxValue", acceptDTO.getMaxValue()==null?null:acceptDTO.getMaxValue().trim());
                map.put("minValue", acceptDTO.getMinValue()==null?null:acceptDTO.getMinValue().trim());
                maps.add(map);
            }
        }
        return maps;
    }

    public static boolean isContains(String container, String[] regx) {
        boolean result = false;
        for (int i = 0; i < regx.length; i++) {
            if (container.indexOf(regx[i]) != -1) {
                return true;
            }
        }
        return result;
    }

}
