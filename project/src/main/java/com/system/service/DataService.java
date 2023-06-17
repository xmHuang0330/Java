package com.system.service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.system.enums.ResultEnum;
import com.system.mapper.DataMapper;
import com.system.pojo.BasicInfo;
import com.system.pojo.ResultInfo;
import com.system.pojo.SearchInfo;
import com.system.utils.ExcelUtil;
import com.system.utils.ToolUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Slf4j
@Service
public class DataService {

  @Autowired
  ExcelUtil excelUtil;

  @Autowired
  DataMapper dataMapper;

  @Autowired
  ToolUtil toolUtil;

  @Autowired
  ResultInfo resultInfo;


  public Map<String, Object> uploadByExcel(MultipartFile file) throws Exception {
    Boolean judge = judge(file);
    Map<String, Object> map = new HashMap<>();
    int i = 0;
    if (!judge) {
      map.put(ResultEnum.ERROR.getCode()+"", ResultEnum.ERROR.getMsg() + " 上传文件不是以.xlsx结尾的");
      return map;
    }
    System.out.println(file.getOriginalFilename());
    if (file.getOriginalFilename().contains("样本信息表")) {
      i = dataMapper.insertSampleInfo(excelUtil.getSampleInfo(file));

    } else {
      List<BasicInfo> basicInfoList = getInfo(file);
      i = dataMapper.insertDataBatch(basicInfoList);
    }

    map.put(ResultEnum.SUCCESS.getCode()+"", ResultEnum.SUCCESS.getMsg() + "，成功插入数据量：" + i);
    return map;
  }


  public ResultInfo search(SearchInfo searchInfo) {
    List<Map<String, Object>> maps = dataMapper.searchByPTTN(searchInfo);
    resultInfo.setCode(ResultEnum.SearchSuccess.getCode());
    resultInfo.setMsg(ResultEnum.SearchSuccess.getMsg());
    resultInfo.setCount(maps.size());
    resultInfo.setData(maps);
    System.out.println(maps.size());
    return resultInfo;
  }




  public ResultInfo info() {
    ResultInfo resultInfo = new ResultInfo();
    List<Map<String, Object>> basicInfos = dataMapper.find();
    resultInfo.setCode(ResultEnum.SUCCESS.getCode());
    resultInfo.setMsg(ResultEnum.SUCCESS.getMsg());
    resultInfo.setCount(basicInfos.size());
    resultInfo.setData(basicInfos);
    log.info( "resultInfo---->" + resultInfo.toString());
    //System.out.println(basicInfos);
    return resultInfo;
  }

  public List<BasicInfo> getInfo(MultipartFile file) throws Exception {
    XSSFWorkbook workBook = (XSSFWorkbook)excelUtil.getWorkBook(file).get(1);
    ArrayList<BasicInfo> basicInfos = new ArrayList<>();
    XSSFSheet sheet = workBook.getSheet("分型结果");
    int lastRowNum = sheet.getLastRowNum();
    int i = 1;
    String name = null;
    while (i <= lastRowNum) {
      BasicInfo basicInfo = new BasicInfo();
      XSSFRow row = sheet.getRow(i);
      XSSFCell cell = row.getCell(0);
      if (cell == null) {
        i++;
        continue;
      }
      CellType cellType = row.getCell(0).getCellType();
      if (cellType == CellType.NUMERIC) name = row.getCell(0).getNumericCellValue() + "";
      else name = row.getCell(0).getStringCellValue();
      basicInfo.setName(name);
      basicInfo.setLane(row.getCell(1).getStringCellValue());
      basicInfo.setIndex((int) row.getCell(2).getNumericCellValue());
      basicInfo.setTablet(row.getCell(3).getStringCellValue());
      basicInfo.setWell(row.getCell(4).getStringCellValue());
      basicInfo.setStutter((int) row.getCell(5).getNumericCellValue());
      basicInfo.setA36((int) row.getCell(6).getNumericCellValue());
      basicInfo.setY45((int) row.getCell(7).getNumericCellValue());
      basicInfos.add(basicInfo);
      i++;
    }
    workBook.close();
    System.out.println("一共有：" + basicInfos.size());
    return basicInfos;
  }

  public Boolean judge(MultipartFile file) {
    return file.getOriginalFilename().endsWith("xlsx") ? true : false;
  }


}
