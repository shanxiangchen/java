
package com.app.service;

import java.util.List;

import com.app.entity.Type;

public interface TypeService {
	List<Type> listPagetype(Type type);
	List<Type> listPagetypes(Type type);
	Type gettypeById(String oddstypeid);
	
	boolean savetype(Type type);
	void savertype(Type type);
	int deletetype(String oddstypeid);
	
}
