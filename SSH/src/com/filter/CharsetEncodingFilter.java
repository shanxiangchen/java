package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * ͳһ�����ַ���
 * @author Administrator
 *
 */
public class CharsetEncodingFilter implements Filter {
	
	private String encoding;
	
	public void destroy() {
	}
	//�Զ��������
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		//�����ַ���
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
		//ȡ�ó�ʼ������
		this.encoding = filterConfig.getInitParameter("encoding");
	}
}
