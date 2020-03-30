//package com.example.demo.service;
//
//import com.example.demo.bo.Account;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * @author: yanuo
// * @create: 20200303 2:54 PM
// */
//@Service
//public class TransferService {
//
//    @Autowired
//    private TransactionService transactionService;
//
//    public void transfer(Account fromAccount, Account toAccount, Integer amount) {
//        transactionService.beginTransaction();
//
//        fromAccount.minus(amount);
//        toAccount.add(amount);
//
//        storageService.update(fromAccount.getUser());
//        storageService.update(fromAccount.getUser());
//
//        transactionService.commit();
//    }
//
//}
