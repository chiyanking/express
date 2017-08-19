package com.dagemen.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2017/2/14.
 */
@WebFilter(filterName="myFilter",urlPatterns="/*")
public class MyFilter implements Filter {

    /**
     * 封装，不需要过滤的请求
     */
    private static final String[] IGNORE_URI = { "/logIn", "/signUp", "/verifyLogin",
            "/css", "/img", "/js", "/fonts", "/kaptcha", "/resetPass", "/register", "/assets", "/forgetpsw"};

    private static final String[] BOOTSTRAP_TABLE_URI = { "/getExaminersListByPage", "/getLabRecordsInfoByClientUUIDAndUsedPage", "/processSampleList",
            "/getSampleList", "/getSampleUseRecordingsList", "/getSequencingResultsList", "/getUsersList", "verificationReportList"};

    @Override
    public void destroy() {
        System.out.println("过滤器销毁");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest hrequest = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = hrequest.getContextPath();

        String basePath = hrequest.getScheme()+"://"+hrequest.getServerName()+":"+hrequest.getServerPort()+path;

        HttpSession session = hrequest.getSession(true);
        Object obj = session.getAttribute("userInfo");

        String requestPath = hrequest.getRequestURI();
        Boolean flag = isContains(requestPath, IGNORE_URI);
        if(!flag && !requestPath.equals("/")){
           if(null == obj){
                 resp.sendRedirect(basePath);
            }else{
                chain.doFilter(hrequest, resp);
            }
        }else{
           chain.doFilter(hrequest, resp);
        }
}

    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("过滤器初始化");
    }

    public boolean isContains(String container, String[] regx) {
        boolean result = false;
        for (int i = 0; i < regx.length; i++) {
            if (container.indexOf(regx[i]) != -1) {
                return true;
            }
        }
        return result;
    }

    /**
     *判断当前请求是否在拦截需求中
     * @param container
     * @param regx
     * @return
     */
}
