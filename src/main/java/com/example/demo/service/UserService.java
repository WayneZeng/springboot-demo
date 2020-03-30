package com.example.demo.service;

import com.example.demo.dal.User;
import com.example.demo.rpc.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service
@Component
public class UserService implements UserServiceApi{

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  public void saveUser(User user) {
    logger.info("save user");
  }


  @Override
  public String getName() {
    return UserService.class.getName();
  }
}
