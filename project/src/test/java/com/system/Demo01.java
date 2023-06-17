package com.system;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
public class Demo01 {

  @Test
  public void test01() {

    String str = "2023-05-06 00:00:00";
    try {
      Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
      String format = new SimpleDateFormat(str).format(date);
      System.out.println(format);
    } catch (ParseException e) {
      System.out.println(e.getErrorOffset());
    }
  }
}
