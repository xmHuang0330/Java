package com.system.mapper;

import com.system.pojo.BasicInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DataMapper {

  int insertDataBatch(List<BasicInfo> basicInfoList);

  List<Map<String, Object>> find();

  List<Map<String, Object>> searchByAY(Integer a36, Integer y45);

  List<Map<String, Object>> searchByName(String name, Integer a36, Integer y45);

  List<Map<String, Object>> searchByLane(String lane, Integer a36, Integer y45);

  List<Map<String, Object>> searchByTablet(String tablet, Integer a36, Integer y45);

  List<Map<String, Object>> searchByNL(String name, String lane, Integer a36, Integer y45);

  List<Map<String, Object>> searchByNT(String name, String tablet, Integer a36, Integer y45);

  List<Map<String, Object>> searchByLT(String lane, String tablet, Integer a36, Integer y45);

  List<Map<String, Object>> searchByBasic(String name, String lane, String tablet, Integer a36, Integer y45);
}
