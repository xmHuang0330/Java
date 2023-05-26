package com.system.controller;

import com.system.service.DataUpLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class DataUpLoadController {

  @Autowired
  DataUpLoadService dataUpLoadService;

  @RequestMapping("/excelUpload")
  public Map<Integer, String> uploadByExcel(File file) {
    return dataUpLoadService.uploadByExcel(file);
  }

}
