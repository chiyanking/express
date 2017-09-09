package com.dagemen.Utils.Kdniao;

import com.alibaba.fastjson.JSON;
import com.dagemen.dto.Kdniao.*;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * 快递鸟电子面单接口
 */
public class KdGoldAPIDemo {

    //电商ID
    private static String EBusinessID="1300851";
    //电商加密私钥，快递鸟提供，注意保管，不要泄漏
    private static String AppKey="cd3dd15d-f4b4-49e0-bf68-6a23af553bb9";
    //请求url, 正式环境地址：http://api.kdniao.cc/api/Eorderservice
//    private String ReqURL="http://testapi.kdniao.cc:8081/api/Eorderservice";
    private String ReqURL="http://api.kdniao.cc/api/Eorderservice";


    public static void main(String[] args) {
        KdGoldAPIDemo kdGoldAPIDemo =new KdGoldAPIDemo();
        try {
            ElectronicSheetRequest electronicSheetRequest = new ElectronicSheetRequest();
            electronicSheetRequest.setOrderCode("012657700387");
            electronicSheetRequest.setShipperCode("EMS");
            electronicSheetRequest.setPayType(1);
            electronicSheetRequest.setExpType(1);
            electronicSheetRequest.setCost(1.0);
            electronicSheetRequest.setOtherCost(1.0);
            electronicSheetRequest.setWeight(1.0);
            electronicSheetRequest.setQuantity(1);
            electronicSheetRequest.setVolume(0.0);
            electronicSheetRequest.setRemark("小心轻放");
            electronicSheetRequest.setIsReturnPrintTemplate(1);

            Sender sender = new Sender();
            sender.setCompany("LV");
            sender.setAddress("西湖区 浙江大学紫金港校区");
            sender.setCityName("上海");
            sender.setMobile("15018442396");
            sender.setName("张三");
            sender.setProvinceName("上海");
            sender.setExpAreaName("青浦区");

            Receiver receiver = new Receiver();
            receiver.setCompany("LV");
            receiver.setAddress("西湖区 浙江大学紫金港校区");
            receiver.setCityName("上海");
            receiver.setMobile("15018442396");
            receiver.setName("张三");
            receiver.setProvinceName("上海");
            receiver.setExpAreaName("青浦区");

            electronicSheetRequest.setReceiver(receiver);
            electronicSheetRequest.setSender(sender);

            Commodity commodity = new Commodity();
            commodity.setGoodsName("鞋子");
            commodity.setGoodsquantity(1);
            commodity.setGoodsWeight(1.0);
            electronicSheetRequest.setCommodity(commodity);
            System.out.print(kdGoldAPIDemo.orderOnlineByJson(electronicSheetRequest));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * Json方式 电子面单
     * @throws Exception
     */
    public ElectronicSheetResponse orderOnlineByJson(ElectronicSheetRequest electronicSheetRequest) throws Exception{
        String requestData = JSON.toJSONString(electronicSheetRequest).toString();
        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", EBusinessID);
        params.put("RequestType", "1007");
        String dataSign=encrypt(requestData, AppKey, "UTF-8");
        params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");
        String result=sendPost(ReqURL, params);
        //根据公司业务处理返回的信息......
        ElectronicSheetResponse response = JSON.parseObject(result, ElectronicSheetResponse.class);
        return response;
    }
    /**
     * MD5加密
     * @param str 内容
     * @param charset 编码方式
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * base64编码
     * @param str 内容
     * @param charset 编码方式
     * @throws UnsupportedEncodingException
     */
    private String base64(String str, String charset) throws UnsupportedEncodingException{
        String encoded = Base64.encode(str.getBytes(charset));
        return encoded;
    }

    @SuppressWarnings("unused")
    private String urlEncoder(String str, String charset) throws UnsupportedEncodingException{
        String result = URLEncoder.encode(str, charset);
        return result;
    }

    /**
     * 电商Sign签名生成
     * @param content 内容
     * @param keyValue Appkey
     * @param charset 编码方式
     * @throws UnsupportedEncodingException ,Exception
     * @return DataSign签名
     */
    @SuppressWarnings("unused")
    private String encrypt (String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception
    {
        if (keyValue != null)
        {
            return base64(MD5(content + keyValue, charset), charset);
        }
        return base64(MD5(content, charset), charset);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param params 请求的参数集合
     * @return 远程资源的响应结果
     */
    @SuppressWarnings("unused")
    private String sendPost(String url, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            if (params != null) {
                StringBuilder param = new StringBuilder();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if(param.length()>0){
                        param.append("&");
                    }
                    param.append(entry.getKey());
                    param.append("=");
                    param.append(entry.getValue());
                    System.out.println(entry.getKey()+":"+entry.getValue());
                }
                System.out.println("param:"+param.toString());
                out.write(param.toString());
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }
}