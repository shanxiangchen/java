package com.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
@SuppressWarnings("rawtypes")
public interface ActivityAndCityGpsMapper {
	int insertActivityAndCityGps(@Param("list")List list);
	int deleteActivityAndCityGps(String actId);
}
