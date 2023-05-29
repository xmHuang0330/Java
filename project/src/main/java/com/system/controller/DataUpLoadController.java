package com.system.controller;

import com.system.service.DataUpLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/upload")
public class DataUpLoadController {

  @Autowired
  DataUpLoadService dataUpLoadService;

  @ResponseBody
  @RequestMapping("/excelUpload")
  public Map<String, Object> uploadByExcel(MultipartFile file) throws Exception {
    HashMap<String, Object> map = new HashMap<>();
    map.put("555", "dsfsd");
    dataUpLoadService.uploadByExcel(file);
    return map;
  }

}
