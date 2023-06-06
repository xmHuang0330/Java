package com.system.service;

import com.system.utils.ExcelUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.*;

@Service
public class SampleService {

  @Autowired
  ExcelUtil excelUtil;

  public void uploadSample(MultipartFile file) throws Exception {
    Map<String, LinkedList<Object>> tablet = Tablet(excelUtil.getWorkBook(file));
    Set<String> strings = tablet.keySet();
    chemicalInfo(file);
  }

  public void judgeWork(MultipartFile file) throws Exception {
    sampleJudge(excelUtil.getWorkBook(file));
  }

  public void chemicalInfo(MultipartFile file) {

  }

  public void sampleJudge(XSSFWorkbook workbook) throws IOException {
    String[] strings = new String[]{"lane1", "lane2", "lane3", "lane4"};
    HashMap<String, String> map = new HashMap<>();
    ArrayList<Object> list = new ArrayList<>();
    String name = null;
    CellType cellType = null;
    for (String sheetName :
      strings) {
      XSSFSheet sheet = workbook.getSheet(sheetName);
      int lastRowNum = sheet.getLastRowNum();
      int l = 1;
      int c = 0;
      while (l <= lastRowNum) {
        XSSFRow row = sheet.getRow(l);
        XSSFCell cell = row.getCell(2);
        if (cell == null) {
          l++;
          continue;
        } else cellType = cell.getCellType();
        if (cellType == CellType.NUMERIC) name = cell.getNumericCellValue() + "";
        else name = cell.getStringCellValue();
        if (map.containsKey(name)) {
          c++;
          list.add(name);
          System.out.println(sheetName + "中的 " + name + " 与 " + map.get(name) + " 中的样本号存在重复");
        } else {
          map.put(name, sheetName);
        }
        l++;
      }
      //System.out.println(sheetName + "单表重复及跨表重复的样本号一共有：" + c);
    }
    workbook.close();
    list.forEach((Object s) -> {
      System.out.println(s);
    });
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
      if (row.getCell(integer).getCellType() == CellType.NUMERIC)
      map.put(tablet, obj);
    }
    return map;
  }

  public Map<String, LinkedList<Object>> Tablet(XSSFWorkbook workbook) {
    HashMap<Object, Object> members = infoSample(workbook,8);
    HashMap<Object, Object> density = infoSample(workbook,7);
    String[] arr = new String[]{"lane1", "lane2", "lane3", "lane4"};
    HashMap<String, LinkedList<Object>> tabletPro = new HashMap<>();
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
        tabletL.append(lane + "_").append(tablet);
        if (tabletList.contains(tablet)) {
          tabletCount++;
          tabletPro.get(tabletL.toString()).remove(1);
          tabletPro.get(tabletL.toString()).add(1,tabletCount);
          continue;
        } else {
          tabletCount = 0;
          tabletCount++;
          tabletList.add(tablet);
          String project = row.getCell(3).getStringCellValue();
          tabletPro.put(tabletL.toString(), new LinkedList<>());
          tabletPro.get(tabletL.toString()).add(0,project);
          tabletPro.get(tabletL.toString()).add(1,tabletCount);
          tabletPro.get(tabletL.toString()).add(2,members.get(tablet));
          tabletPro.get(tabletL.toString()).add(3,density.get(tablet));
        }
      }
    }
    return tabletPro;
  }

}
