package com.xm;

import com.xm.entity.SampleData;
import com.xm.mapper.SampleDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.*;
import java.util.*;

@Slf4j
public class salaryCount {

    @Test
    public void a() throws IOException {

        InputStream is = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        SqlSession session = factory.openSession();
        SampleDataMapper sampleDataMapper = session.getMapper(SampleDataMapper.class);
        //是否超过12000，不超过就没绩效
        //男性常>=27&&Y>=35，女性常>27&&Y==0
        String filePath = "E:\\huangxiaomiao\\files\\A历史\\代码测试\\绩效\\q";
        File file = new File(filePath);
        File[] files = file.listFiles();
        SampleData sampleData = new SampleData();
        sampleData.setTablet("dfff");
        sampleData.setWell("dff");
        sampleData.setMr36aLt(323);
        sampleData.setIndex(232);
        sampleData.setLane("dads");
        sampleData.setSampleName("ddads");
        sampleData.setBglYLt(11);
        for (File f :
                files) {
            List<SampleData> sampleDataList = getInfo(f);
            log.info("获取信息完毕，开始插入");
            log.info(sampleDataList.size()+"");
            sampleDataMapper.insertBatch(sampleDataList);
            log.info("插入完毕");
        }

    }

    public void insInfo() {

    }

    public List<SampleData> getInfo(File file) {
        HashMap<String, ArrayList<Object>> infoMap = new HashMap<>();
        List<SampleData> sampleDataList = new ArrayList<>();
        try {
            System.out.println(file.getName());
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet sheet = workbook.getSheet("分型结果");
            System.out.println(sheet.getSheetName());
            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum; i++) {
                SampleData sampleData = new SampleData();
                Row row = sheet.getRow(i);
                String sampleName = null;
                CellType cellType = row.getCell(0).getCellType();
                if (cellType.equals(CellType._NONE)) {
                    sampleName = "kong";
                } else if (cellType.equals(CellType.NUMERIC)) {
                    sampleName = row.getCell(0).getStringCellValue() + "";
                } else {
                    sampleName = row.getCell(0).getStringCellValue();
                }
                String lane = row.getCell(1).getStringCellValue();
                int index = (int) row.getCell(2).getNumericCellValue();
                String tablet = row.getCell(3).getStringCellValue();
                String well = row.getCell(4).getStringCellValue();
                int a36 = (int) row.getCell(6).getNumericCellValue();
                int y41 = (int) row.getCell(7).getNumericCellValue();
                sampleData.setSampleName(sampleName);
                sampleData.setLane(lane);
                sampleData.setBglYLt(y41);
                sampleData.setIndex(index);
                sampleData.setMr36aLt(a36);
                sampleData.setWell(well);
                sampleData.setTablet(tablet);
                sampleDataList.add(sampleData);
            }

        } catch (IOException e) {
            log.info("IOException：" + e.getMessage());
        }
        return sampleDataList;
    }

    public void qualify(String filePath,Integer normal,Integer y) {
        File file = new File(filePath);
        File[] files = file.listFiles();
        HashMap<String, HashMap<String,ArrayList<String>>> map = new HashMap<>();
        for (File f :
                files) {
            String fileName = f.getName();
            map.put(fileName, new HashMap<>());
            HashMap<String, ArrayList> hashMap = new HashMap<>();
            try {
                FileInputStream fis = new FileInputStream(f);
                XSSFWorkbook workbook = new XSSFWorkbook(fis);
                XSSFSheet sheet = workbook.getSheet("分型结果");
                ArrayList<Integer> integers = localIndex(sheet);

            } catch (FileNotFoundException e) {
                log.info("FileNotFoundException：" + e.getMessage());
            } catch (IOException e) {
                log.info("IOException：" + e.getMessage());
            }
        }
    }



    public ArrayList<Integer> localIndex(Sheet sheet) {
        Row row = sheet.getRow(0);
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            String cellValue = row.getCell(i).getStringCellValue();
            if (cellValue.equals("MR36A_LT")) {
                integers.add(0,i);
            }
            if (cellValue.equals("BGI-Y_LT")) {
                integers.add(1,i);
            }
            if (integers.size() == 2) {
                break;
            }
        }
        return integers;
    }

    public Boolean enough(String path) {
        File file = new File(path);
        File[] files = file.listFiles();
        HashMap<String, Integer> hashMap = new HashMap<>();
        int count = 0;
        for (File file1 : files) {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file1));
                XSSFSheet sheet = workbook.getSheet("一代位点输出统计");
                int numericCellValue = (int) sheet.getRow(0).getCell(2).getNumericCellValue();
                count += numericCellValue;
                hashMap.put(file1.getName(), numericCellValue);
                workbook.close();
            } catch (IOException e) {
                System.out.println("文件输入有误：" + e.getMessage());
            }
        }
        hashMap.keySet().forEach((String s) -> {
            log.info(s + " 下机的样本数为： " + hashMap.get(s));
        });
        log.info("一共下机的样本数为：" + count);
        if (count >= 12000) {
            return true;
        } else {
            return false;
        }
    }
}
