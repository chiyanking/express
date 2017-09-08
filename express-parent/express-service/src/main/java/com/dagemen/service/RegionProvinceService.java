package com.dagemen.service;

import com.dagemen.dto.PCD;
import com.dagemen.entity.RegionProvince;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 行政区域：省份 服务类
 * </p>
 *
 * @author dagemen
 * @since 2017-08-19
 */
public interface RegionProvinceService extends IService<RegionProvince> {
    RegionProvince selectByCode(Long code);


    List<PCD> getPCDTree();

}
