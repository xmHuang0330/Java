package com.system.service;

import com.system.enums.ResultEnum;
import com.system.mapper.AreaMapper;
import com.system.pojo.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AreaService {

  @Autowired
  AreaMapper areaMapper;

  @Autowired
  ResultInfo resultInfo;


  public ResultInfo addArea(String city) {
    int i = areaMapper.addArea(city);
    ResultInfo resultInfo = new ResultInfo();
    resultInfo.setCount(i);
    resultInfo.setMsg(ResultEnum.SUCCESS.getMsg());
    resultInfo.setCode(ResultEnum.SUCCESS.getCode());
    return resultInfo;
  }

  public ResultInfo addAreaBatch(String city, String batch, Integer count) {
    int i = areaMapper.addAreaBatch(city, batch, count);
    ResultInfo resultInfo = new ResultInfo();
    resultInfo.setCount(i);
    resultInfo.setMsg(ResultEnum.SUCCESS.getMsg());
    resultInfo.setCode(ResultEnum.SUCCESS.getCode());
    return resultInfo;
  }

  public ResultInfo updateAreaBatch(String city, String batch, Integer count) {
    int i = areaMapper.updateAreaBatch(city, batch, count);
    ResultInfo resultInfo = new ResultInfo();
    resultInfo.setCount(i);
    resultInfo.setMsg(ResultEnum.SUCCESS.getMsg());
    resultInfo.setCode(ResultEnum.SUCCESS.getCode());
    return resultInfo;
  }
}
