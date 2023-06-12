package com.system.controller;

import com.system.pojo.BasicInfo;
import com.system.pojo.ResultInfo;
import com.system.pojo.SearchInfo;
import com.system.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Slf4j
@RestController
@ResponseBody
@RequestMapping("/data")
public class DataController {

  @Autowired
  DataService dataService;

  @PostMapping("/excelUpload")
  public Map<String, Object> uploadByExcel(MultipartFile file) throws Exception {
    Map<String, Object> resultMap = dataService.uploadByExcel(file);
    return resultMap;
  }

  @PostMapping("/search")
  public ResultInfo search(@RequestBody SearchInfo searchInfo) {
    System.out.println(searchInfo.toString());
    ResultInfo resultInfo = dataService.search(searchInfo);
    return resultInfo;
  }


}
