package com.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class AccessFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

	@Override
	public Object run() {
		// 过滤规则
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		logger.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
		Object accessToken = request.getParameter("accessToken");
		if (accessToken == null) {
			logger.warn("access token is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("please login");
			return null;
		}
		logger.info("access token ok");
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// 该过滤器是否需要执行
		return true;
	}

	@Override
	public int filterOrder() {
		// 过滤器执行顺序
		return 0;
	}

	@Override
	public String filterType() {
		// 请求在路由之前过滤
		return "pre";
	}

}
