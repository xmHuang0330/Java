package com.system.service;

import com.system.enums.ResultEnum;
import com.system.mapper.DataUpLoadMapper;
import com.system.pojo.BasicInfo;
import com.system.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataUpLoadService {

  @Autowired
  ExcelUtil excelUtil;

  @Autowired
  DataUpLoadMapper dataUpLoadMapper;

  public Map<String, Object> uploadByExcel(MultipartFile file) throws Exception {
    Boolean judge = judge(file);
    Map<String, Object> map = new HashMap<>();
    if (!judge) {
      map.put(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg() + " 上传文件不是以.xlsx结尾的");
      return map;
    }
    List<BasicInfo> basicInfoList = getInfo(file);
    int i = dataUpLoadMapper.insertDataBatch(basicInfoList);
    int size = basicInfoList.size();
    if (i == size) map.put(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMsg() + "，成功插入数据量：" + i);
    else map.put(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMsg() + "共：" + size + " 插入：" + i);
    return map;
  }

  public List<BasicInfo> getInfo(MultipartFile file) throws Exception {
    XSSFWorkbook workBook = excelUtil.getWorkBook(file);
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
    return file.getOriginalFilename().endsWith(".xlsx") ? true : false;
  }


}
