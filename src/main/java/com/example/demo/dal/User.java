package com.example.demo.dal;

import lombok.Data;

//@Table(name = "user")
@Data
public class User {

    private Integer id;

    private String userId;

    private String username;

    private Integer balance;

}
