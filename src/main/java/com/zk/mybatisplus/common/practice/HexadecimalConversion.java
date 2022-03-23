package com.zk.mybatisplus.common.practice;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 进制转换类
 * DecimalFormat的用法，十进制转为二进制补0
 * 需求：一个字节8位 (bit)， 需要通过每一位（0或者1）来判断状态的时候，不补0会把0省略掉导致无法判断状态
 */
public class HexadecimalConversion {
    public static void main(String[] args) {

        int a = 4;
        //十进制转二进制 未补0 输出结果：1
        String t0Binary = Integer.toBinaryString(a);
        System.out.println("t0Binary====" + t0Binary);

        //十进制转二进制 补0  输出结果：00000001
        DecimalFormat df = new DecimalFormat("00000000");
        String format = df.format(Integer.valueOf(t0Binary));
        System.out.println("format===" + format);

        //二进制转十进制
        int twoToTen = BinaryToDecimal(110);
        System.out.println("twoToTen===" + twoToTen);

        //十进制转八进制
        String octalString = Integer.toOctalString(9);
        System.out.println("octalString=====" + octalString);

        //八进制转十进制
        int octal = 116;
        int decimal = convertOctalToDecimal(octal);
        System.out.printf("%d 八进制 = %d十进制", octal, decimal);
        System.out.println();

        //十进制转十六进制
        String hexString = Integer.toHexString(18);
        System.out.println("hexString=====" + hexString);

        //十六进制转十进制
        int covertTen = covert(hexString);
        System.out.println("covertTen=======" + covertTen);

    }

    /**
     * 八进制转十进制
     */
    public static int convertOctalToDecimal(int octal) {
        int decimalNumber = 0, i = 0;
        while (octal != 0) {
            decimalNumber += (octal % 10) * Math.pow(8, i);
            ++i;
            octal /= 10;
        }
        return decimalNumber;
    }

    /**
     * 二进制转十进制
     */
    public static int BinaryToDecimal(int binaryNumber) {

        int decimal = 0;
        int p = 0;
        while (true) {
            if (binaryNumber == 0) {
                break;
            } else {
                int temp = binaryNumber % 10;
                decimal += temp * Math.pow(2, p);
                binaryNumber = binaryNumber / 10;
                p++;
            }
        }
        return decimal;
    }

    /**
     * 十六进制转十进制
     */
    public static int covert(String content) {
        int number = 0;
        String[] HighLetter = {"A", "B", "C", "D", "E", "F"};
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i <= 9; i++) {
            map.put(i + "", i);
        }
        for (int j = 10; j < HighLetter.length + 10; j++) {
            map.put(HighLetter[j - 10], j);
        }
        String[] str = new String[content.length()];
        for (int i = 0; i < str.length; i++) {
            str[i] = content.substring(i, i + 1);
        }
        for (int i = 0; i < str.length; i++) {
            number += map.get(str[i]) * Math.pow(16, str.length - 1 - i);
        }
        return number;
    }


}
