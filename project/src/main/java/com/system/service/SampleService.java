package com.system.service;

import com.system.enums.ResultEnum;
import com.system.mapper.TabletMapper;
import com.system.pojo.TabletInfo;
import com.system.utils.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Slf4j
@Service
public class SampleService {

  @Autowired
  ExcelUtil excelUtil;

  @Autowired
  TabletMapper tabletMapper;

  public Map<String, Object> uploadSample(MultipartFile file) throws Exception {
    List list = excelUtil.getWorkBook(file);
    String chip = getChip((XSSFWorkbook) list.get(1));
    return toNvc(Tablet((XSSFWorkbook) list.get(1),(FileInputStream)list.get(0),chip));
    //chemicalInfo(file);

  }

  public void judgeWork(MultipartFile file) throws Exception {
    ArrayList list = excelUtil.getWorkBook(file);
    sampleJudge((XSSFWorkbook) list.get(1),(FileInputStream) list.get(0));
  }

  public void chemicalInfo(MultipartFile file) {

  }

  public TabletInfo searchByChip(String chip) {
    TabletInfo tabletInfo = tabletMapper.searchByChip(chip);
    return tabletInfo;
  }

  public Map<String, Object> toNvc(Map<String, ArrayList<Object>> tablet) {
    ArrayList<TabletInfo> tabletList = new ArrayList<>(tablet.size());
    Map<String, Object> map = new HashMap<>();
    tablet.keySet().stream().forEach((String t) -> {
      TabletInfo tabletInfo = new TabletInfo();
      tabletInfo.setTablet(t);
      tabletInfo.setProject((String)tablet.get(t).get(0));
      tabletInfo.setStartCount((int)tablet.get(t).get(1));
      tabletInfo.setExperimenters((String)tablet.get(t).get(2));
      tabletInfo.setTabletDensity((double)tablet.get(t).get(3));
      tabletInfo.setChip(tablet.get(t).get(4).toString());
      tabletList.add(tabletInfo);
    });
    int i = tabletMapper.insertBatch(tabletList);
    if (i == tablet.size()){ map.put(ResultEnum.SUCCESS.getCode() + "", ResultEnum.SUCCESS.getMsg() + "共：" + i);}
    else map.put(ResultEnum.ERROR.getCode() + "", ResultEnum.ERROR.getMsg());
    return map;

  }

  public String getChip(XSSFWorkbook workbook) {
    XSSFSheet sheet = workbook.getSheet("上机记录");
    String chip = sheet.getRow(13).getCell(1).getStringCellValue();
    return chip;
  }

  public void sampleJudge(XSSFWorkbook workbook, FileInputStream fis) throws Exception {
    String[] strings = new String[]{"lane1", "lane2", "lane3", "lane4"};
    LinkedHashMap<String, ArrayList<Object>> map = new LinkedHashMap<>();
    HashMap<String, String> map1 = new LinkedHashMap<>();
    int index = 0;
    String name;
    for (String sheet : strings) {
      XSSFSheet sheet1 = workbook.getSheet(sheet);
      int lastRowNum = sheet1.getLastRowNum();
      for (int i = 1; i <= lastRowNum; i++) {
        XSSFRow row = sheet1.getRow(i);
        index = (int) row.getCell(0).getNumericCellValue();
        if (row.getCell(2) == null || row.getCell(2).getCellType() == CellType._NONE) continue;
        if (row.getCell(2).getCellType() == CellType.NUMERIC) name = row.getCell(2).getNumericCellValue() + "";
        else name = row.getCell(2).getStringCellValue();
        if (name.equals("")) continue;
        if (!map.containsKey(name)) {
          map.put(name, new ArrayList<>());
          map.get(name).add(sheet);
          map.get(name).add(index);
        } else {
          StringBuilder builder = new StringBuilder();
          StringBuilder builder1 = new StringBuilder();
          builder.append(map.get(name).get(0) + "_").append(map.get(name).get(1));
          builder1.append(sheet + "_").append(index);
          map1.put(builder.toString(), name);
          map1.put(builder1.toString(), name);
        }
      }
      fis.close();
      workbook.close();
      excelUtil.sample(map1);
    }
  }



  public HashMap<Object, Object> infoSample(XSSFWorkbook workbook,int integer) {
    HashMap<Object, Object> map = new LinkedHashMap<>();
    XSSFSheet sheet = workbook.getSheet("建库信息");
    int lastRowNum = sheet.getLastRowNum();
    for (int i = 1; i <= lastRowNum; i++) {
      XSSFRow row = sheet.getRow(i);
      if (row.getCell(2) == null || row.getCell(2).getCellType() == CellType._NONE) continue;
      String tablet = row.getCell(2).getStringCellValue();
      if (tablet.startsWith("A") || tablet.startsWith("T")) continue;
      if (row.getCell(integer) == null) continue;
      Object obj = null;
      if (row.getCell(integer).getCellType() == CellType.NUMERIC) obj = row.getCell(integer).getNumericCellValue();
      else obj = row.getCell(integer).getStringCellValue();
      map.put(tablet, obj);
    }
    return map;
  }

  public Map<String, ArrayList<Object>> Tablet(XSSFWorkbook workbook,FileInputStream fis,String chip) throws IOException {
    HashMap<Object, Object> members = infoSample(workbook,8);
    Set<Object> objects = members.keySet();
    HashMap<Object, Object> density = infoSample(workbook,7);
    String[] arr = new String[]{"lane1", "lane2", "lane3", "lane4"};
    HashMap<String, ArrayList<Object>> tabletPro = new HashMap<>();
    ArrayList<String> tabletList = new ArrayList<>();
    int lastRowNum = 0;
    int tabletCount = 0;
    for (String lane :
      arr) {
      XSSFSheet sheet = workbook.getSheet(lane);
      lastRowNum = sheet.getLastRowNum();
      tabletCount = 0;
      for (int i = 1; i <= lastRowNum; i++) {
        XSSFRow row = sheet.getRow(i);
        StringBuilder tabletL = new StringBuilder();
        String tablet = row.getCell(1).getStringCellValue();
        if (tablet.startsWith("A") || tablet.startsWith("T")) {
          continue;
        }
        tabletL.append(chip + "_").append(lane);
        if (tabletList.contains(tablet)) {
          tabletCount++;
          tabletPro.get(tablet).remove(1);
          tabletPro.get(tablet).add(1,tabletCount);
          continue;
        } else {
          tabletCount = 0;
          tabletCount++;
          tabletList.add(tablet);
          String project = row.getCell(3).getStringCellValue();
          tabletPro.put(tablet, new ArrayList<>());
          tabletPro.get(tablet).add(0,project);
          tabletPro.get(tablet).add(1,tabletCount);
          tabletPro.get(tablet).add(2,members.get(tablet));
          tabletPro.get(tablet).add(3,density.get(tablet));
          tabletPro.get(tablet).add(4,tabletL);
        }
      }
    }
    workbook.close();
    fis.close();
    return tabletPro;
  }

}
