package com.dagemen.helper;

import com.dagemen.dto.WechatSignature;
import com.dagemen.dto.WechatTicket;
import com.dagemen.dto.WechatToken;
import com.dagemen.Utils.json.Jackson;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

@Component
public class TokenHelper {

    Logger logger = LoggerFactory.getLogger(TokenHelper.class);
    private static final String appid = "wx0bbefeae4409621f";

    private static final String appsecret = "36544de2cb3b60f913cf3ac610d51c67";

    private static WechatToken token = null;

    private static WechatTicket ticket = null;


    @Resource
    RestTemplate restTemplate;


    public WechatToken getToken() throws IOException {
        if (token != null && token.isNotExpired()) {
            return token;
        }
        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", appid, appsecret);
        WechatToken wechatToken = restTemplate.getForObject(url, WechatToken.class);
        if (StringUtils.isBlank(wechatToken.getAccess_token())) {
            logger.error("返回数据: 无法获取到token  原因: " + restTemplate.getForObject(url, Map.class));
        }

        logger.info("返回数据: " + Jackson.base().writeValueAsString(wechatToken));
        refreshToken(wechatToken);
        return token;
    }

    public WechatToken refreshToken(WechatToken newToken) {
        if (token == null) {
            token = newToken;
            token.setExpireDate(DateUtil.addSeconds(new Date(), newToken.getExpires_in()));
            return token;
        }
        token.setAccess_token(newToken.getAccess_token());
        token.setExpires_in(newToken.getExpires_in());
        token.setExpireDate(DateUtil.addSeconds(new Date(), newToken.getExpires_in()));
        return token;
    }


    public WechatTicket getTicket() throws IOException {
        if (ticket != null && ticket.isNotExpired()) {
            return ticket;
        }

        String url = String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi", getToken().getAccess_token());
        WechatTicket wechatticket = restTemplate.getForObject(url, WechatTicket.class);
        if (StringUtils.isBlank(wechatticket.getTicket())) {

            logger.error("返回数据: 无法获取到Ticket  原因：" + Jackson.base().writeValueAsString(restTemplate.getForObject(url, Map.class)));
        }

        logger.info("返回数据: " + Jackson.base().writeValueAsString(wechatticket));
        refreshTicket(wechatticket);
        return ticket;
    }

    public WechatTicket refreshTicket(WechatTicket newTicket) {
        if (ticket == null) {
            ticket = newTicket;
            ticket.setExpireDate(DateUtil.addSeconds(new Date(), ticket.getExpires_in()));
            return ticket;
        }
        ticket.setTicket(newTicket.getTicket());
        ticket.setExpires_in(newTicket.getExpires_in());
        ticket.setExpireDate(DateUtil.addSeconds(new Date(), ticket.getExpires_in()));
        return ticket;
    }

    public WechatSignature getSignature(String url) throws IOException {
        String timestamp = String.valueOf(System.currentTimeMillis());
        timestamp = timestamp.substring(0, timestamp.length() - 3);
        String noncestr = String.valueOf(System.currentTimeMillis());
        SortedMap<String, String> map = new TreeMap<String, String>();
        map.put("noncestr", noncestr);
        map.put("jsapi_ticket", getTicket().getTicket());
        map.put("timestamp", timestamp);
        map.put("url", url);

        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.append(entry.getKey());
            builder.append("=");
            builder.append(entry.getValue());
            builder.append("&");
        }
        String substring = builder.substring(0, builder.length() - 1);
        String s = SHA1(substring);
        WechatSignature wechatSignature = new WechatSignature();
        wechatSignature.setSignature(s);
        wechatSignature.setTimestamp(timestamp);
        wechatSignature.setNonceStr(noncestr);
        wechatSignature.setAppId(appid);
        return wechatSignature;
    }

    /**
     * @param decript 要加密的字符串
     * @return 加密的字符串
     * SHA1加密
     */
    public final static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
