package com.system.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {

  SUCCESS(3333, "操作成功"),
  ERROR(4444, "操作失败"),

  ;

  private Integer code;
  private String msg;

  ResultEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
