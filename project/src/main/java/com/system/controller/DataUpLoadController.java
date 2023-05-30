package com.system.controller;

import com.system.service.DataUpLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/upload")
public class DataUpLoadController {

  @Autowired
  DataUpLoadService dataUpLoadService;

  @ResponseBody
  @RequestMapping("/excelUpload")
  public Map<String, Object> uploadByExcel(MultipartFile file) throws Exception {
    Map<String, Object> resultMap = dataUpLoadService.uploadByExcel(file);
    return resultMap;
  }

}
