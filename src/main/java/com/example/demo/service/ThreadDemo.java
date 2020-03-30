package com.example.demo.service;

import lombok.Builder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author: yanuo
 * @create: 20200308 9:09 PM
 */

public class ThreadDemo {

    public static class MyThread implements Runnable {

        public void run() {
            System.out.println(">>>run");
        }
    }

    static class MyCallable implements Callable {

        @Override
        public Object call() throws Exception {
            return 1;
        }
    }

}
