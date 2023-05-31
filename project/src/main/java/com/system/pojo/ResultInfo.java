package com.system.pojo;

import com.system.pojo.BasicInfo;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResultInfo {

  private Integer code;
  private String msg;
  private Integer count;
  private List<BasicInfo> data;
}
