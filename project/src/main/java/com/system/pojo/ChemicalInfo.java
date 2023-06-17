package com.system.pojo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ChemicalInfo {

  private String chip;
  private String lane;
  private String cyclizingWay;
  private Double libraryDensity;
  private Double cyclizingInput;
  private Double cyclizingProductDensity;
  private Double cyclizingSuccessRate;
  private String dnbWay;
  private Double dnbVolume;
  private Double dnbInput;
  private Double dnbProductDensity;
  private String remarks;


}
