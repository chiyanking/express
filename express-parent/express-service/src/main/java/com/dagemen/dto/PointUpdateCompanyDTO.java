package com.dagemen.dto;

/**
 * Created by 丁芙蓉 on 2017/8/27.
 */
public class PointUpdateCompanyDTO {

    private Long pointId;
    private Long companyId;
    private Long expModelId;

    public PointUpdateCompanyDTO() {
    }

    public Long getPointId() {
        return pointId;
    }

    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getExpModelId() {
        return expModelId;
    }

    public void setExpModelId(Long expModelId) {
        this.expModelId = expModelId;
    }
}
