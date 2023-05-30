package com.system.pojo;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix="spring.datasource")
public class BasicInfo {

  private String name;
  private String lane;
  private Integer index;
  private String tablet;
  private String well;
  private Integer stutter;
  private Integer a36;
  private Integer y45;


}
