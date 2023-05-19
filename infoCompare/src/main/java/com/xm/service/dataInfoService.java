package com.xm.service;

import com.xm.entity.SampleData;
import com.xm.mapper.SampleDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class dataInfoService {

    @Autowired
    SampleDataMapper sampleDataMapper;


    public void a() {

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
            //sampleDataMapper.save();
            sampleDataMapper.insertBatch(sampleDataList);
            log.info("插入完毕");
        }

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
}
