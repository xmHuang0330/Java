package com.xm.mapper;

import com.xm.entity.SampleData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SampleDataMapper {
    List<SampleData> findAll();

    boolean save();

    int insertBatch(List<SampleData> sampleDataList);
}
