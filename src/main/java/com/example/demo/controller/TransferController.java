package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.UserDTO;
//import com.example.demo.service.TransferService;
//import com.example.demo.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yanuo
 * @create: 20200303 2:52 PM
 */

@RequestMapping("/api")
@RestController
public class TransferController {

    /**
     * 系统日志
     */
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(TransferController.class);

//    @Autowired
//    private TransferService transferService;

    /**
     * 注册并添加用户信息 如果用户已经存在，直接返回
     */
    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    @ApiOperation(value = "", notes = "")
    public JSONObject trasnfer(@RequestBody UserDTO user) {

        LOGGER.info("写入用户信息：" + user);
        return null;
    }
}
