package com.system.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChipInfo {

  private String chip;
  private String project;
  private String startTime;
  private String location;
  private String endTime;


  public String getChip() {
    return chip;
  }

  public void setChip(String chip) {
    this.chip = chip;
  }


  public String getProject() {
    return project;
  }

  public void setProject(String project) {
    this.project = project;
  }


  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }


  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

}
