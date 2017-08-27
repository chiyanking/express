package com.dagemen.service;

import com.dagemen.dto.ExpressDTO;
import com.dagemen.dto.PointAddressDto;
import com.dagemen.entity.Express;

import java.util.Map;

/**
 * 手机类 service 层
 *
 * @author dagemen
 * @since 2017-08-19
 */
public interface MobileService {
    Express insertExpress(ExpressDTO expressDTO);

    PointAddressDto getPointAddress(long pointId);
}
