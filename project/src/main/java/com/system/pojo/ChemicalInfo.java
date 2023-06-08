package com.system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChemicalInfo {

  private String chip;
  private String project;
  private String members;
  private String startTime;
  private String location;
  private String endTime;
  private String cyclizingWay;
  private double libraryDensity;
  private double cyclizingInput;
  private double cyclizingProductDensity;
  private double cyclizingSuccessRate;
  private String dnbWay;
  private double dnbVolume;
  private double dnbInput;
  private double dnbProductDensity;
  private String remarks;


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


  public String getMenbers() {
    return members;
  }

  public void setMenbers(String members) {
    this.members = members;
  }


  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }


  public String getLacation() {
    return location;
  }

  public void setLacation(String location) {
    this.location = location;
  }


  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }


  public String getSyslizingWay() {
    return cyclizingWay;
  }

  public void setSyslizingWay(String cyclizingWay) {
    this.cyclizingWay = cyclizingWay;
  }


  public double getLibraryDensity() {
    return libraryDensity;
  }

  public void setLibraryDensity(double libraryDensity) {
    this.libraryDensity = libraryDensity;
  }


  public double getCyclizingInput() {
    return cyclizingInput;
  }

  public void setCyclizingInput(double cyclizingInput) {
    this.cyclizingInput = cyclizingInput;
  }


  public double getCyclizingProductDensity() {
    return cyclizingProductDensity;
  }

  public void setCyclizingProductDensity(double cyclizingProductDensity) {
    this.cyclizingProductDensity = cyclizingProductDensity;
  }


  public double getCyclizingSuccessRate() {
    return cyclizingSuccessRate;
  }

  public void setCyclizingSuccessRate(double cyclizingSuccessRate) {
    this.cyclizingSuccessRate = cyclizingSuccessRate;
  }


  public String getDnbWay() {
    return dnbWay;
  }

  public void setDnbWay(String dnbWay) {
    this.dnbWay = dnbWay;
  }


  public double getDnbVolume() {
    return dnbVolume;
  }

  public void setDnbVolume(double dnbVolume) {
    this.dnbVolume = dnbVolume;
  }


  public double getDnbInput() {
    return dnbInput;
  }

  public void setDnbInput(double dnbInput) {
    this.dnbInput = dnbInput;
  }


  public double getDnbProductDensity() {
    return dnbProductDensity;
  }

  public void setDnbProductDensity(double dnbProductDensity) {
    this.dnbProductDensity = dnbProductDensity;
  }


  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

}
