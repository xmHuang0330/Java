package com.system.mapper;

import com.system.pojo.BasicInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataUpLoadMapper {

  int insertDataBatch(List<BasicInfo> basicInfoList);
}
