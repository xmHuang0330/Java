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
  @PostMapping("/excelUpload")
  public Map<String, Object> uploadByExcel(MultipartFile file) throws Exception {
    Map<String, Object> resultMap = dataService.uploadByExcel(file);
    return resultMap;
  }

  @PostMapping("/search")
  public ResultInfo search(@RequestBody BasicInfo basicInfo) {
    ResultInfo resultInfo = dataService.search(basicInfo);
    return resultInfo;
  }



}
