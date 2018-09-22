package com.aspect;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class UserAspect {

	public void before(Object obj) {
		System.out.println("准备打印角色信息！");
	}

	public void after(Object obj) {
		System.out.println("完成角色信息打印！");
	}

	public void afterReturning(Object obj) {
		System.out.println("刚刚完成打印功能!");
	}

	public void afterThrowing(Object obj) {
		System.out.println("打印角色信息异常！");
	}

}
