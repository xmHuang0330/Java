package com.system.controller;

import com.system.pojo.BasicInfo;
import com.system.pojo.ResultInfo;
import com.system.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/data")
public class DataController {

  @Autowired
  DataService dataService;

  @ResponseBody
  @RequestMapping("/excelUpload")
  public Map<String, Object> uploadByExcel(MultipartFile file) throws Exception {
    Map<String, Object> resultMap = dataService.uploadByExcel(file);
    return resultMap;
  }

  @PostMapping("/search")
  public ResultInfo search(@RequestBody BasicInfo basicInfo) {
    log.info(basicInfo.toString());
    ResultInfo resultInfo = dataService.search(basicInfo);
    log.info(resultInfo.toString());
    return resultInfo;
  }

}
