package com.zk.mybatisplus.common.practice;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

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

        //补充 十进制转16进制
        String hexString = Integer.toHexString(18);
        System.out.println(hexString);
        //十六进制转10进制
        int covert = covert(hexString);
        System.out.println(covert);
    }

    /**
     * 十六进制转十进制
     */
    public static int covert(String content){
        int number=0;
        String [] HighLetter = {"A","B","C","D","E","F"};
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0;i <= 9;i++){
            map.put(i+"",i);
        }
        for(int j= 10;j<HighLetter.length+10;j++){
            map.put(HighLetter[j-10],j);
        }
        String[]str = new String[content.length()];
        for(int i = 0; i < str.length; i++){
            str[i] = content.substring(i,i+1);
        }
        for(int i = 0; i < str.length; i++){
            number += map.get(str[i])*Math.pow(16,str.length-1-i);
        }
        return number;
    }

}
