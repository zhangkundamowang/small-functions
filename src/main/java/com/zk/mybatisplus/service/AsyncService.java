package com.zk.mybatisplus.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
@EnableAsync
public class AsyncService {

    /**
     * 采用异步执行
     */
    @Async
    public void execute() {
        // 这里执行实际的业务逻辑，在这里我们就是用一个简单的遍历来模拟
        Arrays.stream(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}).forEach(t -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("获取number为:" + t);
        });
    }
}
