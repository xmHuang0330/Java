package com.system.service.impl;

import java.io.File;
import java.util.Map;

public interface DataUpLoadServiceImpl {

  Map<Integer, String> uploadByExcel(File file);

  Boolean judge(File file);
}
