package com.dagemen.Utils.Kdniao;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/8/18.
 */
public class CompanyCode {

    private static HashMap<String, String> companyCode = new HashMap<String, String>(){

        private static final long serialVersionUID = 4747425769325759613L;
        {
            put("安捷快递","AJ");
            put("安能物流","ANE");
            put("安信达快递","AXD");
            put("北青小红帽","BQXHM");
            put("百福东方","BFDF");
            put("百世快运","BTWL");
            put("CCES快递","CCES");
            put("城市100","CITY100");
            put("COE东方快递","COE");
            put("长沙创一","CSCY");
            put("成都善途速运","CDSTKY");
            put("德邦","DBL");
            put("D速物流","DSWL");
            put("大田物流","DTWL");
            put("EMS","EMS");
            put("快捷速递","FAST");
            put("FEDEX联邦(国内件）","FEDEX");
            put("FEDEX联邦(国际件）","FEDEX_GJ");
            put("飞康达","FKD");
            put("广东邮政","GDEMS");
            put("共速达","GSD");
            put("国通快递","GTO");
            put("高铁速递","GTSD");
            put("汇丰物流","HFWL");
            put("天天快递","HHTT");
            put("恒路物流","HLWL");
            put("天地华宇","HOAU");
            put("华强物流","hq568");
            put("百世快递","HTKY");
            put("华夏龙物流","HXLWL");
            put("好来运快递","HYLSD");
            put("京广速递","JGSD");
            put("九曳供应链","JIUYE");
            put("佳吉快运","JJKY");
            put("嘉里物流","JLDT");
            put("捷特快递","JTKD");
            put("急先达","JXD");
            put("晋越快递","JYKD");
            put("加运美","JYM");
            put("佳怡物流","JYWL");
            put("跨越物流","KYWL");
            put("龙邦快递","LB");
            put("联昊通速递","LHT");
            put("民航快递","MHKD");
            put("明亮物流","MLWL");
            put("能达速递","NEDA");
            put("平安达腾飞快递","PADTF");
            put("全晨快递","QCKD");
            put("全峰快递","QFKD");
            put("全日通快递","QRT");
            put("如风达","RFD");
            put("赛澳递","SAD");
            put("圣安物流","SAWL");
            put("盛邦物流","SBWL");
            put("上大物流","SDWL");
            put("顺丰快递","SF");
            put("盛丰物流","SFWL");
            put("盛辉物流","SHWL");
            put("速通物流","ST");
            put("申通快递","STO");
            put("速腾快递","STWL");
            put("速尔快递","SURE");
            put("唐山申通","TSSTO");
            put("全一快递","UAPEX");
            put("优速快递","UC");
            put("万家物流","WJWL");
            put("万象物流","WXWL");
            put("新邦物流","XBWL");
            put("信丰快递","XFEX");
            put("希优特","XYT");
            put("新杰物流","XJ");
            put("源安达快递","YADEX");
            put("远成物流","YCWL");
            put("韵达快递","YD");
            put("义达国际物流","YDH");
            put("越丰物流","YFEX");
            put("原飞航物流","YFHEX");
            put("亚风快递","YFSD");
            put("运通快递","YTKD");
            put("圆通速递","YTO");
            put("亿翔快递","YXKD");
            put("邮政平邮/小包","YZPY");
            put("增益快递","ZENY");
            put("汇强快递","ZHQKD");
            put("宅急送","ZJS");
            put("众通快递","ZTE");
            put("中铁快运","ZTKY");
            put("中通速递","ZTO");
            put("中铁物流","ZTWL");
            put("中邮物流","ZYWL");
            put("亚马逊物流","AMAZON");
            put("速必达物流","SUBIDA");
            put("瑞丰速递","RFEX");
            put("快客快递","QUICK");
            put("城际快递","CJKD");
            put("CNPEX中邮快递","CNPEX");
            put("鸿桥供应链","HOTSCM");
            put("海派通物流公司","HPTEX");
            put("澳邮专线","AYCA");
            put("泛捷快递","PANEX");
            put("PCA Express","PCA");
            put("UEQ Express","UEQ");
        }

    };
    public static String getCompanyCode(String companyName) {
        if (companyName == null) {
            return null;
        }
        return companyCode.get(companyName);
    }
}
