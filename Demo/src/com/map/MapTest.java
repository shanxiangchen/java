package com.map;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MapTest {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("maminghui", "DDA");
		map.put("huyadong", "DDC");
		map.put("chenshanxiang", "DDD");
		System.out.println("第一种：通过Map.keySet遍历key和value：");
		for (String key : map.keySet()) {
			String str = map.get(key);
			System.out.println(key + "	" + str);
		}
		System.out.println("第二种：通过Map.entrySet使用iterator遍历key和value：");
		Iterator<Entry<String, String>> ite = map.entrySet().iterator();
		while (ite.hasNext()) {
			Map.Entry<String, String> entry = ite.next();
			System.out.println(entry.getKey() + "	" + entry.getValue());
		}
		System.out.println("第三种：通过Map.entrySet遍历key和value：");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "	" + entry.getValue());
		}
		System.out.println("第四种：通过Map.values()遍历所有的value，但不能遍历key：");
		for (String value : map.values()) {
			System.out.println("value= " + value);
		}
	}
}
