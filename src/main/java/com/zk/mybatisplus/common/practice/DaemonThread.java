package com.zk.mybatisplus.common.practice;

import java.util.concurrent.TimeUnit;

public class DaemonThread {
    public static void main(String[] args) throws InterruptedException {
        //添加一个钩子（Hook）线程, 用来监听 JVM 退出，并输出日志；
        Runtime.getRuntime().addShutdownHook(new Thread(()-> System.out.println("JVM exit success")));
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(2);//推荐使用TimeUnit
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("这是守护线程");
                    //System.out.println("這是用戶綫程");
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("main()主线程退出");
    }
}
