package com.zk.mybatisplus.common.pollingthread;

import java.io.File;
import java.util.concurrent.locks.Lock;

/**
 * 视频文件分片上传的时候需要在生成index.m3u8文件后再推送视频流地址
 * 轮询查询m3u8文件是否生成，生成后启用轮询线程去推送视频流地址
 * 主线程轮询查找文件夹 一旦生成index.m3u8文件结束轮询
 */

public class MainThread {
    public static void main(String[] args) {
        String url = "H:\\file\\1.txt";
        PollingThread pollingThread = new PollingThread();
        pollingThread.setNestId("1");
        pollingThread.setUrl(url);
        new Thread(pollingThread).start();
        File file = new File("H:\\file\\1.txt");
        Boolean b = true;
        while (b) {
            System.out.println("轮询查找文件中");
            //判断文件是否存在
            while (file.exists() && b) {
                //激活轮询线程
                synchronized (Lock.class) {
                    Lock.class.notify();
                }
                b = false;
            }
        }
    }
}
