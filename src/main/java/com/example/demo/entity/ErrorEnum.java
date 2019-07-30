package com.example.demo.entity;

import org.springframework.util.StringUtils;

/**
 * @author: yanuo
 * @create: 20190520 4:06 PM
 */

public enum ErrorEnum {
  OK("0", "ok"),
  COMMON_ERROE("1", "error"),

  USER_EXISTED("2", "用户已经存在"),
  USER_NOTFOUND("3", "找不到用户"),

  MISSING_PARAM("4", "参数缺失");

  public String code;
  public String message;

  ErrorEnum(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public static ErrorEnum getByCode(String code) {
    if (!StringUtils.isEmpty(code)) {
      ErrorEnum[] var1 = values();
      int var2 = var1.length;

      for (int var3 = 0; var3 < var2; ++var3) {
        ErrorEnum openErrorEnum = var1[var3];
        if (openErrorEnum.getCode().equals(code)) {
          return openErrorEnum;
        }
      }
    }

    return null;
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return this.message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
