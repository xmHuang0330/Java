package com.system.mapper;

import com.system.pojo.BasicInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataMapper {

  int insertDataBatch(List<BasicInfo> basicInfoList);

  List<Map<String, Object>> find();

  List<Map<String, Object>> searchByPTTN(String project,String name,String tablet,String type);

}
