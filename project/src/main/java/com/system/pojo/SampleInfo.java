package com.system.pojo;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SampleInfo {

  private String chip;
  private String lane;
  private Integer index;
  private String tablet;
  private String name;
  private String projectName;
  private String preName;
  private String well;

  public String getPreName() {
    return preName;
  }

  public void setPreName(String preName) {
    this.preName = preName;
  }

  public String getChip() {
    return chip;
  }

  public void setChip(String chip) {
    this.chip = chip;
  }


  public String getLane() {
    return lane;
  }

  public void setLane(String lane) {
    this.lane = lane;
  }


  public long getIndex() {
    return index;
  }

  public void setIndex(Integer index) {
    this.index = index;
  }


  public String getTablet() {
    return tablet;
  }

  public void setTablet(String tablet) {
    this.tablet = tablet;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }


  public String getWell() {
    return well;
  }

  public void setWell(String well) {
    this.well = well;
  }

}
