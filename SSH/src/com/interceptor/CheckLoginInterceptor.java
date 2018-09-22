package com.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CheckLoginInterceptor implements Interceptor {
	
	private static final long serialVersionUID = 1L;

	public void destroy() {
		
	}

	public void init() {
		
	}
	//×Ô¶¨ÒåÀ¹½ØÆ÷
	public String intercept(ActionInvocation arg0) throws Exception {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		if(session.get("user.name")!=null)
		{
			return arg0.invoke();
		}
		return "login";
	}



}
