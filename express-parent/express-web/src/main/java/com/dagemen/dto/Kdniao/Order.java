package com.dagemen.dto.Kdniao;

/**
 * Created by Administrator on 2017/9/5.
 */
public class Order {

    /**
     * 订单编号
     */
    private String OrderCode;

    /**
     * 快递公司编码
     */
    private String ShipperCode;

    /**
     * 快递单号
     */
    private String LogisticCode;

    /**
     * 大头笔
     */
    private String MarkDestination;

    /**
     * 始发地区域编码
     */
    private String OriginCode;

    /**
     * 始发地/始发网点
     */
    private String OriginName;

    /**
     * 目的地区域编码
     */
    private String DestinatioCode;

    /**
     * 目的地/到达网点
     */
    private String DestinatioName;

    /**
     * 分拣编码
     */
    private String SortingCode;

    /**
     * 集包编码
     */
    private String PackageCode;

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    public String getShipperCode() {
        return ShipperCode;
    }

    public void setShipperCode(String shipperCode) {
        ShipperCode = shipperCode;
    }

    public String getLogisticCode() {
        return LogisticCode;
    }

    public void setLogisticCode(String logisticCode) {
        LogisticCode = logisticCode;
    }

    public String getMarkDestination() {
        return MarkDestination;
    }

    public void setMarkDestination(String markDestination) {
        MarkDestination = markDestination;
    }

    public String getOriginCode() {
        return OriginCode;
    }

    public void setOriginCode(String originCode) {
        OriginCode = originCode;
    }

    public String getOriginName() {
        return OriginName;
    }

    public void setOriginName(String originName) {
        OriginName = originName;
    }

    public String getDestinatioCode() {
        return DestinatioCode;
    }

    public void setDestinatioCode(String destinatioCode) {
        DestinatioCode = destinatioCode;
    }

    public String getDestinatioName() {
        return DestinatioName;
    }

    public void setDestinatioName(String destinatioName) {
        DestinatioName = destinatioName;
    }

    public String getSortingCode() {
        return SortingCode;
    }

    public void setSortingCode(String sortingCode) {
        SortingCode = sortingCode;
    }

    public String getPackageCode() {
        return PackageCode;
    }

    public void setPackageCode(String packageCode) {
        PackageCode = packageCode;
    }
}
