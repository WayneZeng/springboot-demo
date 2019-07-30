package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.example.demo.entity.ErrorEnum;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author: yanuo
 * @create: 20190520 4:03 PM
 */
public class BaseController {

  @Value("${wechat.mini-program.appid}")
  public String appid;

  @Value("${wechat.mini-program.secret}")
  public String secret;

  JSONObject formatOkReturn(String data) {

    JSONObject result = new JSONObject();
    result.put("code", ErrorEnum.OK.getCode());
    result.put("message", ErrorEnum.OK.getMessage());

    if (!org.springframework.util.StringUtils.isEmpty(data)) {
      result.put("data", JSONObject
          .parseObject(data, JSONObject.class, Feature.IgnoreNotMatch));
    } else {
      result.put("data", new JSONObject());
    }


    return result;
  }

  JSONObject formatOkReturn(JSON data) {

    JSONObject result = new JSONObject();
    result.put("code", ErrorEnum.OK.getCode());
    result.put("message", ErrorEnum.OK.getMessage());
    result.put("data", data);

    return result;
  }


  JSONObject formatErrorReturn(JSON data, String code, String message) {
    JSONObject result = new JSONObject();
    result.put("code", code);
    result.put("message", message);
    result.put("data", data);

    return result;
  }

}
