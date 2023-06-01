package com.system.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {

  SUCCESS(1001, "操作成功"),
  ERROR(1002, "操作失败"),
  SearchInfoEmpty(1003,"搜索值为空"),
  SearchInfoIsnull(1004,"搜索值不能为null"),
  SearchSuccess(0,"")

  ;

  private Integer code;
  private String msg;

  ResultEnum(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }
}
