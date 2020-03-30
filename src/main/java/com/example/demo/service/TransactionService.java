//package com.example.demo.service;
//
//import org.springframework.stereotype.Service;
//
///**
// * @author: yanuo
// * @create: 20200303 3:12 PM
// */
//
//@Service
//public class TransactionService {
//
//    private ConnectionUtils connectionUtils;
//
//    public void setConnectionUtils(ConnectionUtils connectionUtils) {
//        this.connectionUtils = connectionUtils;
//    }
//    /**
//     * 开启事务
//     */
//    public void beginTransaction(){
//        try {
//            connectionUtils.getThreadConnection().setAutoCommit(false);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    /**
//     * 提交事务
//     */
//    public void commit(){
//        try {
//            connectionUtils.getThreadConnection().commit();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    /**
//     * 回滚事务
//     */
//    public void rollback(){
//        try {
//            connectionUtils.getThreadConnection().rollback();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    /**
//     * 释放连接
//     */
//    public void release(){
//        try {
//            connectionUtils.getThreadConnection().close();//还回连接池中
//            connectionUtils.removeConnection();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//}
