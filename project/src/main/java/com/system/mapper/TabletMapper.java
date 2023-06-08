package com.system.mapper;

import com.system.pojo.TabletInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TabletMapper {
  int insertBatch(List<TabletInfo> list);

  TabletInfo searchByChip(String chip);
}
