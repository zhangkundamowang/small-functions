package com.zk.mybatisplus.common.practice.jsonignore;

import com.alibaba.fastjson.annotation.JSONField;

public class User {

    @JSONField(serialize = false)
    transient int age;

    String name;

    //transient标注在属性上可以序列化和反序列化时忽略该属性
    //transient  student student;

    student student;

    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public com.zk.mybatisplus.common.practice.jsonignore.student getStudent() {
        return student;
    }

    public void setStudent(com.zk.mybatisplus.common.practice.jsonignore.student student) {
        this.student = student;
    }
}


