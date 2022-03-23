package com.zk.mybatisplus.common.practice;

public class ThreadMethodJoin {

    public static void main(String[] args) throws InterruptedException {
        ThreadDemo t1 = new ThreadDemo("A");
        ThreadDemo t0 = new ThreadDemo("B");
        t1.start();
        t0.start();
        t1.join();
    }
}

class ThreadDemo extends Thread {
    private String name;

    public ThreadDemo(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(name + "-" + i);
        }
    }
}