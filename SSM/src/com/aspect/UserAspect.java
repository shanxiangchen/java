package com.aspect;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class UserAspect {

	public void before(Object obj) {
		System.out.println("׼����ӡ��ɫ��Ϣ��");
	}

	public void after(Object obj) {
		System.out.println("��ɽ�ɫ��Ϣ��ӡ��");
	}

	public void afterReturning(Object obj) {
		System.out.println("�ո���ɴ�ӡ����!");
	}

	public void afterThrowing(Object obj) {
		System.out.println("��ӡ��ɫ��Ϣ�쳣��");
	}

}
