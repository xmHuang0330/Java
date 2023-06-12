package com.system.controller;

import com.system.pojo.ResultInfo;
import com.system.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/area")
public class AreaController {

  @Autowired
  AreaService areaService;

  @GetMapping("/addArea/{city}")
  public ResultInfo insertArea(@PathVariable(value = "city") String city) {
     return areaService.addArea(city);
  }

  @GetMapping("/addAreaBatch")
  public ResultInfo insertAreaBatch(@RequestParam("city") String city,@RequestParam("batch") String batch,@RequestParam("count") Integer count) {
    return areaService.addAreaBatch(city, batch, count);
  }

  @GetMapping("/updateAreaBatch")
  public ResultInfo updateAreaBatch(@RequestParam("city") String city,@RequestParam("batch") String batch,@RequestParam("count") Integer count) {
    return areaService.updateAreaBatch(city, batch, count);
  }
}
