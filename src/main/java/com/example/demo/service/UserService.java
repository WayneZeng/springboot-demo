package com.example.demo.service;

import com.example.demo.dal.User;
import com.example.demo.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private UserDao userDao;

  public void saveUser(User user) {
    logger.info("");
    userDao.save(user);
  }



}
