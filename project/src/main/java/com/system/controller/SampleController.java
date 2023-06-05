package com.system.controller;

import com.system.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequestMapping("/sample")
public class SampleController {

  @Autowired
  SampleService sampleService;

  @ResponseBody
  @RequestMapping("/sampleInfo")
  public Map<String, Object> uploadSample(MultipartFile file) throws Exception {
    sampleService.uploadSample(file);
    return null;
  }
}
