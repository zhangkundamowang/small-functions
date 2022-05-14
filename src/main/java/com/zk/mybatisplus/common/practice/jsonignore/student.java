package com.zk.mybatisplus.common.practice.jsonignore;

import com.alibaba.fastjson.annotation.JSONField;

public class student {

   @JSONField(serialize = false)
   int height;

    //transient标注在属性上可以序列化和反序列化时忽略该属性
    //transient int  height;

    String weight;

    public student(int height, String weight) {
        this.height = height;
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}


