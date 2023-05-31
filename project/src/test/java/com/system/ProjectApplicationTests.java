package com.system;

import com.system.service.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = ProjectApplication.class)
class ProjectApplicationTests {

  @Autowired
  DataService dataService;


  @Test
  void contextLoads() {


  }

}
