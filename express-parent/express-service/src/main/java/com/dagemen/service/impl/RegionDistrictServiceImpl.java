package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.dao.RegionDistrictMapper;
import com.dagemen.entity.RegionDistrict;
import com.dagemen.service.RegionDistrictService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行政区域：区域 服务实现类
 * </p>
 *
 * @author dagemen
 * @since 2017-08-19
 */
@Service
public class RegionDistrictServiceImpl extends ServiceImpl<RegionDistrictMapper, RegionDistrict> implements RegionDistrictService {

    @Override
    public RegionDistrict selectByCode(Long code) {
        if(code==null){
            return  null;
        }
        RegionDistrict regionDistrict = new RegionDistrict();
        regionDistrict.setCode(code);
        return selectOne(new EntityWrapper<>(regionDistrict));
    }
}
