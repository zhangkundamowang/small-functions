package com.zk.mybatisplus.common.pollingthread;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;

/**
 * 线程可以传参
 * index.m3u8一旦生成就只执行一次推送
 */
public class PollingThread extends Thread implements Runnable {
    private String nestId;
    private String url;
    public void setNestId(String nestId) {
        this.nestId = nestId;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        File file = new File(url);
        Boolean b = true;
        while (b) {
            while (file.exists() && b) {
                System.out.println("执行run方法");
                pushVideo(nestId, url);
                b = false;
            }
            //让线程阻塞
            synchronized (Lock.class) {
                try {
                    Lock.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void pushVideo(String nestId, String url) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("key", nestId);
        map.put("data", url);
        System.out.println("map===" + map);
    }
}
