package com.zk.mybatisplus.common.lunxun;

public class Message {
    private String content;
    public Message(String content)
    {
        this.content=content;
    }
    public void display(){
        System.out.println(content);
    }
}