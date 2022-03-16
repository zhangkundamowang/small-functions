package com.zk.mybatisplus.common.lunxun;

import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.locks.Lock;
// 这个轮询线程功能很简单，一直不停的轮询队列，一旦队列中有消息进入，就将它出队并调用display()方法输出内容
public class PollingThread extends Thread implements Runnable {
    public static Queue<Message> queue = new LinkedTransferQueue<Message>();

    @Override
    public void run() {
        while (true) {
            while (!queue.isEmpty()) {
                queue.poll().display();
            }
            //把队列中的消息全部打印完之后让线程阻塞
            synchronized (Lock.class) {
                try {
                    Lock.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}