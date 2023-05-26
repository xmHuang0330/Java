package com.system.service;

import com.system.service.impl.DataUpLoadServiceImpl;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

@Service
public class DataUpLoadService implements DataUpLoadServiceImpl {

  @Override
  public Map<Integer, String> uploadByExcel(File file) {
    Boolean judge = judge(file);
    return null;
  }

  @Override
  public Boolean judge(File file) {
    return null;
  }
}
