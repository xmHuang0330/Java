package com.system.controller;

import com.system.pojo.ResultInfo;
import com.system.service.SampleService;
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
  public Map<String, Object> judgeSample(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
    sampleService.judgeWork(file);
    return null;
  }

  @ResponseBody
  @GetMapping(value = "/searchByTablet/{tablet}")
  public ResultInfo searchByTablet(@PathVariable(value = "tablet") String tablet) {
    return sampleService.searchByTablet(tablet);
  }

  @ResponseBody
  @GetMapping(value = "/searchByChip/{chip}")
  public ResultInfo searchByChip(@PathVariable(value = "chip") String chip) {
    return sampleService.searchByChip(chip);
  }

  @ResponseBody
  @DeleteMapping(value = "/deleteByChip/{chip}")
  public ResultInfo deleteByChip(@PathVariable(value = "chip") String chip) {
    System.out.println(chip);
    return sampleService.deleteByChip(chip);
  }

}
