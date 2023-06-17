package com.system;

import com.system.service.DataService;
import io.swagger.models.auth.In;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@SpringBootTest(classes = ProjectApplication.class)
class ProjectApplicationTests {

  @Autowired
  DataService dataService;


  @Test
  void contextLoads() throws IOException {
    String s = "C:\\Users\\dr\\Desktop\\20230615深圳四种检出率";
    HashMap<String, LinkedHashMap<String, Integer>> map = new LinkedHashMap<>();
    File file = new File(s);
    File[] files = file.listFiles();
    int lastRowNum = 0;
    int allSamples = 0;
    int lastCellNum = 0;
    for (File f :
      files) {
      String name = f.getName();
      map.put(name, new LinkedHashMap<>());
      System.out.println(name);
      FileInputStream fis = new FileInputStream(f);
      XSSFWorkbook workbook = new XSSFWorkbook(fis);
      XSSFSheet sheet = workbook.getSheet("分型结果");
      lastRowNum = sheet.getLastRowNum();
      allSamples = lastRowNum;
      for (int i = 1; i <= lastRowNum; i++) {
        XSSFRow row = sheet.getRow(i);
        lastCellNum = (int)row.getLastCellNum();
      }
      int all = get5286all(sheet, lastRowNum, lastCellNum);
      map.get(name).put("样本数总量为：", allSamples);
      map.get(name).put("52+86全位点检出：", all);
      HashMap<String, Integer> a31Y41 = getA31Y41(sheet, lastRowNum);
      for (String s1 :
        a31Y41.keySet()) {
        map.get(name).put(s1, a31Y41.get(s1));
      }
      workbook.close();
    }
    int size = map.keySet().size();

    writeOut(map);
  }

  public void writeOut(HashMap<String, LinkedHashMap<String, Integer>> map) throws IOException {

    FileInputStream fis = new FileInputStream("sample.xlsx");
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    XSSFSheet sheet = workbook.getSheet("sample");
    for (int i = 0; i < 150; i++) {
      XSSFRow row = sheet.createRow(i);
      XSSFCell cell = row.createCell(0);
      row.createCell(1);
      row.createCell(2);
      row.createCell(3);
    }
    int j = 0;
    for (String s :
      map.keySet()) {
      System.out.println(s);
      sheet.getRow(j++).getCell(0).setCellValue(s);
      for (String s1 :
        map.get(s).keySet()) {
        sheet.getRow(j).getCell(1).setCellValue(s1);
        sheet.getRow(j++).getCell(2).setCellValue(map.get(s).get(s1));
      }
    }
    FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\dr\\Desktop\\20230615深圳四种检出率\\result.xlsx");
    workbook.write(fileOutputStream);
    fileOutputStream.close();
    workbook.close();
  }

  public HashMap<String, Integer> getA31Y41(Sheet sheet, int lastRowNum) {
    HashMap<String, Integer> map = new LinkedHashMap<>();
    int count = 0;
    int a31y41 = 0;
    int a27y35 = 0;
    int a30y35 = 0;
    for (int i = 1; i <= lastRowNum; i++) {
      Row row = sheet.getRow(i);
      int a36 = (int) row.getCell(6).getNumericCellValue();
      int y45 = (int) row.getCell(7).getNumericCellValue();
      if (a36 >= 27 && y45 >= 35) {
        a27y35++;
        if (a36 >= 30 && y45 >= 35) {
          a30y35++;
        }
        if (a36 >= 31 && y45 >= 41) {
          a31y41++;
        }
      }
    }
    map.put("常31+Y41个位点检出 (以阅微36和YDB48的位点为标准)：", a31y41);
    map.put("常27+Y35个位点检出 (以阅微36和YDB48的位点为标准)：", a27y35);
    map.put("打拐入库标准的常30+Y35：", a30y35);
    System.out.println("常31+Y41个位点检出 (以阅微36和YDB48的位点为标准)：" + a31y41);
    System.out.println("常27+Y35个位点检出 (以阅微36和YDB48的位点为标准)：" + a27y35);
    return map;
  }

  public int get5286all(Sheet sheet, int lastRowNum, int lastCellNum) {
    int count = lastRowNum - 1;
    int count1 = 0;
    int count2 = 0;
    System.out.println("kkkkkkkkkk");
    String type = "hhh";
    for (int i = 1; i < lastRowNum; i++) {
      Row row = sheet.getRow(i);
      boolean all = true;
      count1++;
      type = null;
      for (int j =8 ; j <= 141; j++) {
        if (row.getCell(j).getCellType() == CellType.NUMERIC) {
          type = row.getCell(j).getNumericCellValue() + "";
        }
        if (row.getCell(j).getCellType() == CellType.STRING) {
          type = row.getCell(j).getStringCellValue();
        }
        if (type.equals("")) {
          all = false;
        }
        if (!all) {
          count--;
          break;
        }
      }
    }
    System.out.println(count + "----------" + count1);
    System.out.println(count + "----------" + count2);
    return count;
  }

}
