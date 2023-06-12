package com.system.utils;

import com.system.pojo.BasicInfo;
import com.system.pojo.ResultInfo;
import com.system.pojo.SearchInfo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ToolUtil {


  public Boolean isInit(SearchInfo searchInfo) {
    if (searchInfo.getName() == null && searchInfo.getProject() == null
        && searchInfo.getTablet() == null && searchInfo.getType() == null) return true;
    else if (searchInfo.getName().equals("") && searchInfo.getProject().equals("")
      && searchInfo.getTablet().equals("") && searchInfo.getType().equals("")) return true;
    else return false;
  }

  public ResultInfo result(List<Map<String,Object>> maps) {
    ResultInfo resultInfo = new ResultInfo();
    resultInfo.setData(maps);
    resultInfo.setCount(maps.size());
    return resultInfo;
  }

  public ResultInfo resCount(Integer count) {
    ResultInfo resultInfo = new ResultInfo();
    resultInfo.setCount(count);
    resultInfo.setData(new ArrayList<>());
    return resultInfo;
  }

  public boolean isAll(SearchInfo searchInfo) {
    if (searchInfo.getType().equals("全部")) return true;
    return false;
  }
  public boolean isMan(SearchInfo searchInfo) {
    if (searchInfo.getType().equals("男性")) return true;
    return false;
  }
  public boolean isWomen(SearchInfo searchInfo) {
    if (searchInfo.getType().equals("女性")) return true;
    return false;
  }
  public boolean isFail(SearchInfo searchInfo) {
    if (searchInfo.getType().equals("不合格")) return true;
    return false;
  }

  public boolean hasName(SearchInfo searchInfo) {
    if (!searchInfo.getName().equals("") && searchInfo.getName() != null) return true;
    return false;
  }

  public boolean hasTablet(SearchInfo searchInfo) {
    if (!searchInfo.getTablet().equals("") && searchInfo.getTablet() != null) return true;
    return false;
  }

}
