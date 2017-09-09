package com.dagemen.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.dagemen.dao.RegionProvinceMapper;
import com.dagemen.dto.PCD;
import com.dagemen.entity.RegionCity;
import com.dagemen.entity.RegionDistrict;
import com.dagemen.entity.RegionProvince;
import com.dagemen.service.RegionCityService;
import com.dagemen.service.RegionDistrictService;
import com.dagemen.service.RegionProvinceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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


    @Resource
    RegionCityService cityService;


    @Resource
    RegionDistrictService districtService;


    private static List tree = null;

    @Override
    public RegionProvince selectByCode(Long code) {
        if (code == null)
            return null;
        RegionProvince regionProvince = new RegionProvince();
        regionProvince.setCode(code);
        return selectOne(new EntityWrapper<>(regionProvince));
    }

    @Override
    public List<PCD> getPCDTree() {

        if (tree != null) {
            return tree;
        }
        RegionProvince province = new RegionProvince();
        EntityWrapper<RegionProvince> param = new EntityWrapper<>(province);
        List<RegionProvince> provinces = selectList(param);

        RegionCity regionCity = new RegionCity();
        List<RegionCity> cities = cityService.selectList(new EntityWrapper<>(regionCity));

        RegionDistrict district = new RegionDistrict();
        List<RegionDistrict> districts = districtService.selectList(new EntityWrapper<>(district));

        List<PCD> listTree = new ArrayList<>();

        for (RegionProvince regionProvince : provinces) {
            PCD PCD = new PCD();
            PCD.setLabel(regionProvince.getName());
            PCD.setValue(regionProvince.getCode());
            PCD.setChildren(new ArrayList<>());
            listTree.add(PCD);
        }
        List<PCD> cityList = new ArrayList<>();
        cities.forEach((item) -> {
            listTree.forEach((parent) -> {
                if (parent.getValue().equals(item.getParentCode())) {
                    PCD pcd = new PCD();
                    pcd.setValue(item.getCode());
                    pcd.setLabel(item.getName());
                    pcd.setChildren(new ArrayList<>());
                    parent.getChildren().add(pcd);
                    cityList.add(pcd);
                }
            });
        });

        districts.forEach((item) -> {
            cityList.forEach((parent) -> {
                if (parent.getValue().equals(item.getParentCode())) {
                    PCD pcd = new PCD();
                    pcd.setValue(item.getCode());
                    pcd.setLabel(item.getName());
                    parent.getChildren().add(pcd);
                }
            });
        });
        return this.tree = listTree;
    }

}
