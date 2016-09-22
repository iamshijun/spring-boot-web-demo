package com.kibou.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

public class WebUtils {
	
	public static Pattern ipv4Pattern = Pattern.compile("(?:\\d{1,3}\\.){3}\\d{1,3}");

	public static String getRemoteAddr(HttpServletRequest request) {
		if (request == null) {
			return "127.0.0.1";//localhost
		}
		//X-Forward-For(XFF:X-Forwarded-For（XFF）是用来识别通过HTTP代理或负载均衡方式连接到Web服务器的客户端最原始的IP地址的HTTP请求头字段), X-IP,
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip != null) {
			Matcher matcher = ipv4Pattern.matcher(ip);
			if (matcher.find()) {
				ip = matcher.group(0);
			}
		}
		return ip;
	}
	
	public static String getFullURL(HttpServletRequest request,boolean includingParams){
		String scheme = request.getScheme();
		int port = request.getServerPort();
		
		String requestURI = request.getRequestURI();
		String serverName = request.getServerName(); //host?
		String queryString = "";
		String contextPath = request.getContextPath();
		if(includingParams){
			queryString = request.getQueryString();
			if(StringUtils.isEmpty(queryString)){
				request.getParameterMap();//TODO 拼接
			}
		}
		String pathInfo = request.getPathInfo();
		
		return new StringBuilder()
			.append(scheme).append("://").append(serverName)
			.append(port == 80 ? "" : ":" + port)
			.append(contextPath)
			.append(requestURI)
			.append(StringUtils.isEmpty(pathInfo) ? "" : pathInfo)
			.append(includingParams ? "?" + queryString : "")
			.toString();
	}
	
	
	public static void main(String[] args) {
		Matcher matcher = ipv4Pattern.matcher("10.36.127.78");
		if (matcher.find()) {
			System.out.println(matcher.group(0));
		}
	}
}
