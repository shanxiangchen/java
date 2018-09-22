package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 统一设置字符集
 * @author Administrator
 *
 */
public class CharsetEncodingFilter implements Filter {
	
	private String encoding;
	
	public void destroy() {
	}
	//自定义过滤器
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		//设置字符集
		servletRequest.setCharacterEncoding(encoding);
		filterChain.doFilter(servletRequest, servletResponse);
//		String contentType = servletRequest.getContentType();
//		if(contentType != null && contentType.toUpperCase().indexOf("UTF-8") != -1){
//			servletRequest.setCharacterEncoding("UTF-8");
//			servletResponse.setCharacterEncoding("UTF-8");
//		}else{
//			servletRequest.setCharacterEncoding("GBK");
//			servletResponse.setCharacterEncoding("GBK");
//		}
//		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		//取得初始化参数
		this.encoding = filterConfig.getInitParameter("encoding");
	}
}
