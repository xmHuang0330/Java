package com.xm;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
public class readExcel {

    @Test
    public void excelData() {
        String files = "E:\\huangxiaomiao\\files\\2023\\深圳\\5月\\位点检出界点比例";
        File file = new File(files);
        File[] files1 = file.listFiles();
        HashMap<String, ArrayList<Integer>> map = new HashMap<>();
        int count = 0;
        for (File f :
                files1) {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(f);
                String name = f.getName();
                count = 0;
                ArrayList<Integer> list = new ArrayList<>();
                map.put(name, list);
                XSSFSheet sheet = workbook.getSheet("一代位点输出统计");
                int sampleCount = (int)sheet.getRow(0).getCell(2).getNumericCellValue();
                System.out.println(name + "中有 " + sampleCount + " 个样本");
                XSSFSheet sheet1 = workbook.getSheet("分型结果");
                int lastRowNum = sheet1.getLastRowNum();
                XSSFRow row = sheet1.getRow(0);
                for (int i = 0; i < 30; i++) {
                    String cellValue = row.getCell(i).getStringCellValue();
                    if (cellValue.equals("MR36A_LT")) {
                        for (int j = 1; j <= lastRowNum; j++) {
                            int a36 = (int)sheet1.getRow(j).getCell(i).getNumericCellValue();
                            if (a36 >= 29) {
                                count++;
                            }
                        }
                    }
                }
                list.add(0, sampleCount);
                list.add(1, count);
            } catch (IOException e) {
                System.out.println("io" + e.getMessage());
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }

        }
        int size = map.size();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("E:\\huangxiaomiao\\files\\2023\\深圳\\5月\\比例.xlsx"));
            workbook.createSheet("位点检出");
            XSSFSheet sheet = workbook.getSheet("位点检出");
            int a = 0;
            int b = 0;
            for (String s :
                    map.keySet()) {
                b = 0;
                XSSFRow row = sheet.createRow(a++);
                row.createCell(b++).setCellValue(s);
                row.createCell(b++).setCellValue(map.get(s).get(0));
                row.createCell(b).setCellValue(map.get(s).get(1));
            }
            workbook.write(new FileOutputStream("E:\\huangxiaomiao\\files\\2023\\深圳\\5月\\比例.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
