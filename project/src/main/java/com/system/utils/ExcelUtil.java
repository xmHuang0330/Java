package com.system.utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.Map;

@Component
public class ExcelUtil {

  public XSSFWorkbook getWorkBook(MultipartFile file) throws Exception {

    InputStream is = file.getInputStream();
    XSSFWorkbook workbook = new XSSFWorkbook(is);
    return workbook;

  }

  public XSSFWorkbook getWorkBook(File file) throws Exception {
    FileInputStream fis = new FileInputStream(file);
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    return workbook;
  }

  public void sample(Map<String,String> map) throws Exception {
    File file = new File("sample.xlsx");
    XSSFWorkbook workBook = getWorkBook(file);
    XSSFSheet sheet = workBook.getSheet("sample");
    int size = map.size();
    for (int i = 0; i <= size; i++) {
      XSSFRow row = sheet.createRow(i);
      row.createCell(0);
      row.createCell(1);
    }
    int j = 0;
    for (String s :
      map.keySet()) {
      String s1 = map.get(s);
      XSSFRow row = sheet.getRow(j++);
      row.getCell(0).setCellValue(s);
      row.getCell(1).setCellValue(s1);
    }
    FileOutputStream fos = new FileOutputStream("C:\\Users\\dr\\Desktop\\sample.xlsx");
    workBook.write(fos);
    workBook.close();
  }
}
