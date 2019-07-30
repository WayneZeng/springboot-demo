package com.example.demo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author: yanuo
 * @create: 20190612 8:11 PM
 */

@Data
public class UserDTO {

  @JSONField(name="user_name")
  private String userName;

}
