package com.system.mapper;

import com.system.pojo.TabletInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface TabletMapper {

  int insertBatch(List<TabletInfo> list);

  ArrayList<Map<String,Object>> searchByChip(String chip);

  int deleteByChip(String chip);

  ArrayList<Map<String,Object>> searchByTablet(String tablet);
}
