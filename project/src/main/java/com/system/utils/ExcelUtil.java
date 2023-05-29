package com.system.utils;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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
}
