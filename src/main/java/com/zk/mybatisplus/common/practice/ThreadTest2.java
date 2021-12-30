package com.zk.mybatisplus.common.practice;

public class ThreadTest2 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("sleep before");
            cal();
            System.out.println("sleep after");
        }, "t1");
        thread.setDaemon(true);
        thread.start();
        System.out.println("end");
    }

    public static void cal() {
        for(int i = 0;i < 10000;i ++) {
            System.out.println("=="+ i);
        }
    }
}
