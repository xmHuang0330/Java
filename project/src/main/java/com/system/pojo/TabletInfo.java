package com.system.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TabletInfo {

  private String chip;
  private String lane;
  private String tablet;
  private String project;
  private String experimenters;
  private double tabletDensity;
  private int startCount;


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


  public String getTablet() {
    return tablet;
  }

  public void setTablet(String tablet) {
    this.tablet = tablet;
  }


  public String getProject() {
    return project;
  }

  public void setProject(String project) {
    this.project = project;
  }


  public String getExperimenters() {
    return experimenters;
  }

  public void setExperimenters(String experimenters) {
    this.experimenters = experimenters;
  }


  public double getTabletDensity() {
    return tabletDensity;
  }

  public void setTabletDensity(double tabletDensity) {
    this.tabletDensity = tabletDensity;
  }

  public long getStartCount() {
    return startCount;
  }

  public void setStartCount(int startCount) {
    this.startCount = startCount;
  }

}
