package com.dagemen.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KuaidiniaoConfig {
    /**
     * 物流跟踪URL
     */
    public static String expressTraceUrl;

    public static String appKey;

    public static String eBusinessID;

    @Value("${express.trace.url}")
    public void setExpressTraceUrl(String expressTraceUrl) {
        this.expressTraceUrl = expressTraceUrl;
    }
    @Value("${express.appKey}")
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Value("${express.eBusinessID}")
    public void setEBusinessID(String eBusinessID) {
        this.eBusinessID = eBusinessID;
    }
}
