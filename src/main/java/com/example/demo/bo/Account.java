package com.example.demo.bo;

import com.example.demo.dal.User;
import lombok.Data;

/**
 * @author: yanuo
 * @create: 20200303 2:57 PM
 */

@Data
public class Account {

    private User user;

    public void minus(Integer amount) {
        if (user.getBalance() - amount < 0) {
            throw new RuntimeException();
        }

        user.setBalance(user.getBalance() - amount);
    }

    public void add(Integer amount) {
        user.setBalance(user.getBalance() + amount);
    }

}
