package com.dagemen.Utils.json;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ser.BeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import org.apache.commons.lang3.StringUtils;

/**
 * Copyright (C), 杭州典击科技有限公司
 * FileName: DynamicFieldFilterProvider
 * Author: Clark
 * Date: 2016/9/9 16:36
 * Description:
 */
@JsonFilter("dynamicField")
public class DynamicFieldFilterProvider extends FilterProvider {
    public static final String DYNAMIC_FIELD_FILTER = "dynamicField";
    private FilterProvider filterProvider;
    private PropertyFilter runtimeFilter;

    DynamicFieldFilterProvider(FilterProvider filterProvider, PropertyFilter runtimeFilter) {
        this.filterProvider = filterProvider;
        this.runtimeFilter = runtimeFilter;
    }

    /** @deprecated */
    @Deprecated
    public BeanPropertyFilter findFilter(Object filterId) {
        throw new UnsupportedOperationException("Access to deprecated filters not supported");
    }

    public PropertyFilter findPropertyFilter(Object filterId, Object valueToFilter) {
        return StringUtils.equals("dynamicField", filterId.toString())?this.runtimeFilter:(this.filterProvider != null?super.findPropertyFilter(filterId, valueToFilter):null);
    }
}
