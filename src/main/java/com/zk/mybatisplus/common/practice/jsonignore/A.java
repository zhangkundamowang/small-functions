package com.zk.mybatisplus.common.practice.jsonignore;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;

public class A {
    public static void main(String[] args) throws JsonProcessingException {
        User user=new User();
        user.setAge(1);
        user.setName("zs");
        user.setStudent(new student(1,"2"));

        //    @JSONField(serialize = false)注解结合fastjson实现忽略部分属性
        String s = JSON.toJSONString(user);
        System.out.println(s);
        //System.out.println(new ObjectMapper().writeValueAsString(user));
    }
}
