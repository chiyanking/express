package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.dao.RegionProvinceMapper;
import com.dagemen.entity.RegionProvince;
import com.dagemen.service.RegionProvinceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行政区域：省份 服务实现类
 * </p>
 *
 * @author dagemen
 * @since 2017-08-19
 */
@Service
public class RegionProvinceServiceImpl extends ServiceImpl<RegionProvinceMapper, RegionProvince> implements RegionProvinceService {

    @Override
    public RegionProvince selectByCode(Long code) {
        if(code==null)
            return null;
        RegionProvince regionProvince = new RegionProvince();
        regionProvince.setCode(code);
        return selectOne(new EntityWrapper<>(regionProvince));
    }
}
