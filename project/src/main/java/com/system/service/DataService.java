package com.system.service;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DataService {

  @Autowired
  ExcelUtil excelUtil;

  @Autowired
  DataMapper dataMapper;

  @Autowired
  ToolUtil toolUtil;


  public Map<String, Object> uploadByExcel(MultipartFile file) throws Exception {
    Boolean judge = judge(file);
    Map<String, Object> map = new HashMap<>();
    if (!judge) {
      map.put(ResultEnum.ERROR.getCode()+"", ResultEnum.ERROR.getMsg() + " 上传文件不是以.xlsx结尾的");
      return map;
    }
    List<BasicInfo> basicInfoList = getInfo(file);
    int i = dataMapper.insertDataBatch(basicInfoList);
    int size = basicInfoList.size();
    if (i == size) map.put(ResultEnum.SUCCESS.getCode()+"", ResultEnum.SUCCESS.getMsg() + "，成功插入数据量：" + i);
    else map.put(ResultEnum.ERROR.getCode()+"", ResultEnum.ERROR.getMsg() + "共：" + size + " 插入：" + i);
    return map;
  }


  public void typeOf(SearchInfo searchInfo) {


  }

  public void typeOf(String project,String type) {

  }
  public void typeOf(String project,String type,String name) {

  }

  public void typeOf(String project,String type,String name,String tablet) {

  }

  public ResultInfo search(SearchInfo searchInfo) {
    ResultInfo resultInfo = new ResultInfo();
    List<Map<String, Object>> basicInfos = null;
    Boolean init = toolUtil.isInit(searchInfo);
    System.out.println(init);
    if (init) {
      basicInfos = dataMapper.find();
    } else if (toolUtil.hasTablet(searchInfo) && toolUtil.hasName(searchInfo)) {

    } else if (toolUtil.hasTablet(searchInfo) && !toolUtil.hasName(searchInfo)){

    }else if (!toolUtil.hasTablet(searchInfo) && toolUtil.hasName(searchInfo)){

    }
    resultInfo.setCode(ResultEnum.SearchSuccess.getCode());
    resultInfo.setMsg(ResultEnum.SearchSuccess.getMsg());
    resultInfo.setCount(basicInfos.size());
    resultInfo.setData(basicInfos);
    return resultInfo;
  }

  /*public List<Integer> standard(BasicInfo basicInfo) {
    ArrayList<Integer> integers = new ArrayList<>();
    int a36 = 27,y45 = 35;
    Integer a361 = basicInfo.getA36();
    Integer y451 = basicInfo.getY45();
    if (a361 != null) a36 = a361;
    if (y451 != null) y45 = y451;
    integers.add(0,a36);
    integers.add(1,y45);
    return integers;
  }*/




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
