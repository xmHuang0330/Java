package com.xm.controller;

import com.xm.service.DataInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/insert")
public class insertInfoController {

  @Autowired
  DataInfoService dataInfoService;

  @RequestMapping("upLoadByExcel")
  public Map insertInfo() {

    return null;
  }
}
