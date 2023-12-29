package com.expand.hucloud.common.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hdq
 * @time 2023/12/28 22:40
 */
public class IpConfig {


    /**
     * 获取远程IP
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        return ip;
    }
}
