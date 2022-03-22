package com.zk.mybatisplus.common.lunxun;

import java.util.concurrent.locks.Lock;

//Main()方法中循环创建消息将它放入队列
public class Main {
    public static void main(String[] args){
        PollingThread pollingThread=new PollingThread();
        pollingThread.start();
        int i=1;
        while(i<=10000) {
            PollingThread.queue.offer(new Message("新消息"+i));
            i++;
            //有消息入队后激活轮询线程
            synchronized (Lock.class) {
                Lock.class.notify();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}