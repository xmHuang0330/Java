package com.system.service;

import com.system.utils.ExcelUtil;
import io.swagger.models.auth.In;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class SampleService {

  @Autowired
  ExcelUtil excelUtil;

  public void uploadSample(MultipartFile file) throws Exception {
    XSSFWorkbook workBook = excelUtil.getWorkBook(file);
    Map<String, LinkedList<Object>> tablet = getTablet(workBook);
    Set<String> strings = tablet.keySet();
  }

  public HashMap<String, String> getMembers(XSSFWorkbook workbook) {
    HashMap<String, String> map = new LinkedHashMap<>();
    XSSFSheet sheet = workbook.getSheet("建库信息");
    int lastRowNum = sheet.getLastRowNum();
    for (int i = 1; i <= lastRowNum; i++) {
      XSSFRow row = sheet.getRow(i);
      if (row.getCell(2) == null || row.getCell(2).getCellType() == CellType._NONE) continue;
      String tablet = row.getCell(2).getStringCellValue();
      if (tablet.startsWith("A") || tablet.startsWith("T")) continue;
      if (row.getCell(8) == null) continue;
      String members = row.getCell(8).getStringCellValue();
      map.put(tablet, members);
    }
    return map;
  }

  public Map<String, LinkedList<Object>> getTablet(XSSFWorkbook workbook) {
    HashMap<String, String> members = getMembers(workbook);
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

        }

      }
    }


    return tabletPro;
  }

}
