package com.dagemen.service;

import com.dagemen.dto.ExpressDTO;
import com.dagemen.dto.PointAddressDTO;

/**
 * 手机类 service 层
 *
 * @author dagemen
 * @since 2017-08-19
 */
public interface MobileService {
    boolean insertExpress(ExpressDTO expressDTO);

    PointAddressDTO getPointAddress(long pointId);
}
