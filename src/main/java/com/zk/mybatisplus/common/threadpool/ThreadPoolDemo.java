package com.zk.mybatisplus.common.threadpool;

import java.util.concurrent.*;

public class ThreadPoolDemo {
 
    public static void main(String[] args) {
 
        ExecutorService executorService = new ThreadPoolExecutor(2, 2, 0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
 
        for (int i = 0; i < 10; i++) {
            int index = i;
            executorService.submit(() -> System.out.println("i:" + index + 
                    " executorService"));
        }
        executorService.shutdown();
    }
}