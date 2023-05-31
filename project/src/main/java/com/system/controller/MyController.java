package com.system.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

  @RequestMapping("/")
  public String helloSB() {
    return "index";
  }
}
