package com.dagemen.dto.Kdniao;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Administrator on 2017/9/5.
 * 商品信息
 */
public class Commodity {

    /**
     * 商品名称
     */
    private String GoodsName;
    /**
     * 商品重量kg
     */
    private Double GoodsWeight;
    /**
     * 商品编码
     */
    private String GoodsCode;
    /**
     * 商品价格
     */
    private Double GoodsPrice;
    /**
     * 商品数量
     */
    private Integer Goodsquantity;
    /**
     * 商品描述
     */
    private String GoodsDesc;
    /**
     * 商品体积m3
     */
    private Double GoodsVol;

    @JSONField(name = "GoodsWeight")
    public Double getGoodsWeight() {
        return GoodsWeight;
    }

    public void setGoodsWeight(Double goodsWeight) {
        GoodsWeight = goodsWeight;
    }

    @JSONField(name = "GoodsPrice")
    public Double getGoodsPrice() {
        return GoodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        GoodsPrice = goodsPrice;
    }

    @JSONField(name = "Goodsquantity")
    public Integer getGoodsquantity() {
        return Goodsquantity;
    }

    public void setGoodsquantity(Integer goodsquantity) {
        Goodsquantity = goodsquantity;
    }

    @JSONField(name = "GoodsVol")
    public Double getGoodsVol() {
        return GoodsVol;
    }

    public void setGoodsVol(Double goodsVol) {
        GoodsVol = goodsVol;
    }

    @JSONField(name = "GoodsName")
    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName == null ? "": goodsName;
    }


    @JSONField(name = "GoodsCode")
    public String getGoodsCode() {
        return GoodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        GoodsCode = goodsCode == null ? "": goodsCode;
    }


    @JSONField(name = "GoodsDesc")
    public String getGoodsDesc() {
        return GoodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        GoodsDesc = goodsDesc == null ? "": goodsDesc;
    }
}
