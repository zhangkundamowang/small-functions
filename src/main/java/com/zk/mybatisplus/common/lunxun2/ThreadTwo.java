package com.zk.mybatisplus.common.lunxun2;

import java.io.File;
import java.util.concurrent.locks.Lock;

//视频分片上传时 需要在生成index.m3u8文件后再推送视频流地址
public class ThreadTwo {
    public static void main(String[] args) {
        String url="H:\\file\\1.txt";
        ThreadTest2 threadTest2 = new ThreadTest2();
        threadTest2.setNestId("1");
        threadTest2.setUrl(url);
        new Thread(threadTest2).start();
        File file = new File("H:\\file\\1.txt");
        Boolean  b = true;
        while (b) {
            System.out.println("轮询查找文件中");
            //判断文件是否存在
            while (file.exists()&&b) {
                //激活轮询线程
                synchronized (Lock.class) {
                    Lock.class.notify();
                }
                b =false;
            }

        }
    }
}
