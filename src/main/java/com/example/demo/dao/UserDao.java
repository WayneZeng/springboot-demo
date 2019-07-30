package com.example.demo.dao;

import com.example.demo.dal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends
    JpaRepository<User, Object> {

  //根据姓名信息，检索返回符合条件的用户
  User findByUsername(String username);

}
