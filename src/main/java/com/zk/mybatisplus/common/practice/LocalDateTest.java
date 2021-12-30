package com.zk.mybatisplus.common.practice;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTest {
    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());

/*打印结果：
2020-04-02
11:29:52.206
2020-04-02T11:29:52.206
*/
    }
}
