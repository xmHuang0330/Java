package com.system.controller;

import com.system.pojo.TabletInfo;
import com.system.service.SampleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/sample")
public class SampleController {

  @Autowired
  SampleService sampleService;

  @ResponseBody
  @PostMapping("/sampleInfo")
  public Map<String, Object> uploadSample(MultipartFile file) throws Exception {
    return sampleService.uploadSample(file);
  }

  @ResponseBody
  @PostMapping("/judgeSample")
  public Map<String, Object> judgeSample(MultipartFile file, HttpServletRequest request) throws Exception {
    sampleService.judgeWork(file);
    return null;
  }

  @ResponseBody
  @GetMapping(value = "/byChip")
  public TabletInfo searchByChip(@RequestParam(value = "chip") String chip) {
    sampleService.searchByChip(chip);
    return null;
  }

}
