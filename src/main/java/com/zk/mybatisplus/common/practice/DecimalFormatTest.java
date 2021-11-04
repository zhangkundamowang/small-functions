package com.zk.mybatisplus.common.practice;

import java.text.DecimalFormat;

/**
 * DecimalFormat的用法，十进制转为二进制补0
 * 需求：一个字节8位 (bit)， 需要通过每一位（0或者1）来判断状态的时候，不补0会把0省略掉导致无法判断状态
 */
public class DecimalFormatTest {
    public static void main(String[] args) {

        int a = 1;
        //十进制转二进制 未补0
        String bin = Integer.toBinaryString(a);
        System.out.println(bin);//1

        //十进制转二进制 补0
        DecimalFormat df = new DecimalFormat("00000000");
        String format = df.format(Integer.valueOf(bin));
        System.out.println(format);//00000001
    }
}
