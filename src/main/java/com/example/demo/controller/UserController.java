package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/queswerServer")
@RestController
public class UserController extends BaseController {

  /**
   * 系统日志
   */
  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  /**
   * 注册并添加用户信息 如果用户已经存在，直接返回
   */
  @RequestMapping(value = "/addUser", method = RequestMethod.POST)
  @ApiOperation(value = "注册并添加用户信息", notes = "注册并添加用户信息")
  public JSONObject add(@RequestBody UserDTO user) {

    LOGGER.info("写入用户信息：" + user);
    return formatOkReturn(new JSONObject());
  }
}