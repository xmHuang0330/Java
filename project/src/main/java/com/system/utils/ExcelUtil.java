package com.system.utils;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;

@Component
public class ExcelUtil {

  public ArrayList getWorkBook(MultipartFile file) throws Exception {
    InputStream fis = file.getInputStream();
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    ArrayList<Object> list = new ArrayList<>();
    list.add(0,fis);
    list.add(1,workbook);
    return list;

  }

  public ArrayList getWorkBook(File file) throws Exception {
    FileInputStream fis = new FileInputStream(file);
    XSSFWorkbook workbook = new XSSFWorkbook(fis);
    ArrayList<Object> list = new ArrayList<>();
    list.add(0,fis);
    list.add(1,workbook);
    return list;
  }

  public void sample(Map<String,String> map) throws Exception {
    File file = new File("sample.xlsx");
    ArrayList list = getWorkBook(file);
    XSSFWorkbook workBook = (XSSFWorkbook)list.get(1);
    FileInputStream fis = (FileInputStream)list.get(0);
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
    fis.close();
    workBook.close();
    fos.close();
  }
}
