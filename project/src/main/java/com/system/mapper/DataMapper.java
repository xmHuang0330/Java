package com.system.mapper;

import com.system.pojo.BasicInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper {

  int insertDataBatch(List<BasicInfo> basicInfoList);

  List<BasicInfo> find();

  List<BasicInfo> searchByAY(Integer a36, Integer y45);

  List<BasicInfo> searchByName(String name, Integer a36, Integer y45);

  List<BasicInfo> searchByLane(String lane, Integer a36, Integer y45);

  List<BasicInfo> searchByTablet(String tablet, Integer a36, Integer y45);

  List<BasicInfo> searchByNL(String name, String lane, Integer a36, Integer y45);

  List<BasicInfo> searchByNT(String name, String tablet, Integer a36, Integer y45);

  List<BasicInfo> searchByLT(String lane, String tablet, Integer a36, Integer y45);

  List<BasicInfo> searchByBasic(String name, String lane, String tablet, Integer a36, Integer y45);
}
