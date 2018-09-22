package com.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Main {

	public static void main(String[] args) {
		streamBeanTest();
	}

	public static void streamBeanTest() {
		Men men = Main.getMen();
		String xml = toXml(men);
		System.out.println(xml);
		men = (Men) toBean(xml, new Men());
		System.out.println(men.getWife().getEmail());
	}

	public static Men getMen() {
		Men men = new Men();
		men.setId("521");
		men.setName("Tom");
		men.setPassword("123456");
		men.setEmail("Tom@sina.com");
		Wife wf = new Wife();
		wf.setId("748");
		wf.setName("Kitty");
		wf.setPassword("654321");
		wf.setEmail("Kitty@168.com");
		men.setWife(wf);
		return men;
	}

	public static String toXml(Object obj) {
		XStream xstream = new XStream(new DomDriver("utf-8"));
		xstream.processAnnotations(obj.getClass());
		xstream.alias(obj.getClass().getSimpleName().toLowerCase(),
				obj.getClass());
		return xstream.toXML(obj);
	}

	public static Object toBean(String xmlStr, Object obj) {
		XStream xstream = new XStream(new DomDriver("utf-8"));
		xstream.processAnnotations(obj.getClass());
		xstream.alias(obj.getClass().getSimpleName().toLowerCase(),
				obj.getClass());
		return xstream.fromXML(xmlStr);
	}

}
