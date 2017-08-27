package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.dao.RegionCityMapper;
import com.dagemen.entity.RegionCity;
import com.dagemen.service.RegionCityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 行政区域：城市 服务实现类
 * </p>
 *
 * @author dagemen
 * @since 2017-08-19
 */
@Service
public class RegionCityServiceImpl extends ServiceImpl<RegionCityMapper, RegionCity> implements RegionCityService {

    @Override
    public RegionCity selectByCode(Long code) {
        if(code==null){
            return null;
        }
        RegionCity regioncity = new RegionCity();
        regioncity.setCode(code);
        return selectOne(new EntityWrapper(regioncity));
    }
}
