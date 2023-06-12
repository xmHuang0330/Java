package com.system.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AreaMapper {

  int addArea(String city);

  int addAreaBatch(String city, String batch, Integer count);

  int updateAreaBatch(String city, String batch, Integer count);
}
