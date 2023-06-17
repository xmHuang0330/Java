package com.system.utils;

import com.system.pojo.ChemicalInfo;
import com.system.pojo.ChipInfo;
import com.system.pojo.SampleInfo;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;


import java.io.*;
import java.util.*;

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

  public String getChip(XSSFWorkbook workbook) {
    return workbook.getSheet("上机记录").getRow(13).getCell(1).getStringCellValue();
  }

  public void getChemicalInfo(XSSFWorkbook workbook, String chip) throws Exception {
    ArrayList<ChemicalInfo> chemicalInfoList = new ArrayList<>();
    XSSFSheet sheet = workbook.getSheet("上机记录");
    HashMap<String, LinkedList<Object>> map = new HashMap<>();
    int i = 1;
    int j = 1;
    int lastCellNum = sheet.getRow(i).getLastCellNum();
    CellType cellType = null;
    System.out.println(lastCellNum);
    while (i < 5) {
      XSSFRow row = sheet.getRow(i);
      ChemicalInfo chemicalInfo = new ChemicalInfo();
      chemicalInfo.setChip(chip);
      chemicalInfo.setLane(row.getCell(1).getStringCellValue());
      chemicalInfo.setCyclizingWay(row.getCell(2).getStringCellValue());
      chemicalInfo.setLibraryDensity(row.getCell(3).getNumericCellValue());
      i++;
    }
  }

  public ArrayList<SampleInfo> getSampleInfo(MultipartFile file) throws Exception {
    ArrayList<SampleInfo> sampleInfos = new ArrayList<>();
    ArrayList list = getWorkBook(file);
    XSSFWorkbook workBook = (XSSFWorkbook) list.get(1);
    FileInputStream fis = (FileInputStream)list.get(0);
    String chip = getChip(workBook);
    String[] sheets = new String[]{"lane1", "lane2", "lane3", "lane4"};
    for (String lane :
      sheets) {
      XSSFSheet sheet1 = workBook.getSheet(lane);
      int lastRowNum = sheet1.getLastRowNum();
      for (int i = 1; i <= lastRowNum; i++) {
        XSSFRow row = sheet1.getRow(i);
        int index = (int) row.getCell(0).getNumericCellValue();
        String tablet = row.getCell(1).getStringCellValue();
        String name = null;
        if (row.getCell(2).getCellType() == CellType._NONE) name = " ";
        else if (row.getCell(2).getCellType() == CellType.NUMERIC) name = row.getCell(2).getNumericCellValue() + "";
        else name = row.getCell(2).getStringCellValue();
        String project = null;
        String projectName = null;
        if (row.getCell(3).getCellType() == CellType._NONE) {
          project = "";
          projectName = "";
        } else {
          project = StringUtils.substringBefore(row.getCell(3).getStringCellValue(),"-");
          projectName = StringUtils.substringAfter(row.getCell(3).getStringCellValue(),"-");

        }
        String well = null;
        if (row.getCell(7).getCellType() == CellType._NONE) well = "";
        else well = row.getCell(7).getStringCellValue();
        SampleInfo sampleInfo = new SampleInfo();
        sampleInfo.setChip(chip);
        sampleInfo.setLane(lane);
        sampleInfo.setIndex(index);
        sampleInfo.setTablet(tablet);
        sampleInfo.setName(name);
        sampleInfo.setPreName(project);
        sampleInfo.setProjectName(projectName);
        sampleInfo.setWell(well);
        sampleInfos.add(sampleInfo);
      }
    }
    getChemicalInfo(workBook,chip);
    workBook.close();
    fis.close();
    return sampleInfos;
  }
}
