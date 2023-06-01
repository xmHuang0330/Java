package com.system.utils;

import com.system.pojo.BasicInfo;
import org.springframework.stereotype.Component;

@Component
public class ToolUtil {

  public Boolean isEmpty(BasicInfo basicInfo) {
    System.out.println(basicInfo);
    if (basicInfo.getName().equals("") && basicInfo.getLane().equals("")
          && basicInfo.getTablet().equals("") && basicInfo.getA36() == null
            && basicInfo.getY45() == null) return true;
    else return false;

  }

  public Boolean isInit(BasicInfo basicInfo) {
    if (basicInfo.getName() == null && basicInfo.getLane() == null
            && basicInfo.getTablet() == null && basicInfo.getA36() == null
              && basicInfo.getY45() == null) return true;
    else return false;
  }

  public Boolean isNull(BasicInfo basicInfo) {
    if (basicInfo.getName().equals("null") || basicInfo.getLane().equals("null")
       || basicInfo.getTablet() .equals("null")) return true;
    else return false;
  }
}
