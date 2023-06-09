package com.system.pojo;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultInfo {

  private Integer code = 0;
  private String msg = "";
  private Integer count;
  private List<Map<String,Object>> data;
}
