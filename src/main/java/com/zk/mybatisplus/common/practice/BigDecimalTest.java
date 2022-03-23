package com.zk.mybatisplus.common.practice;

import java.math.BigDecimal;

public class BigDecimalTest {
    public static void main(String[] args) {
        BigDecimal bignum1 = new BigDecimal("7.25");
        BigDecimal bignum2 = new BigDecimal("5.00");
        BigDecimal bignum3 = null;

        //加法
        bignum3 = bignum1.add(bignum2);
        System.out.println("和  是：" + bignum3);

        //减法
        bignum3 = bignum1.subtract(bignum2);
        System.out.println("差  是：" + bignum3);

        //乘法 保留一位小数
        bignum3 = bignum1.multiply(bignum2).setScale(1, BigDecimal.ROUND_HALF_DOWN);
        System.out.println("积  是：" + bignum3);

        //除法
        bignum3 = bignum1.divide(bignum2);
        System.out.println("商  是：" + bignum3);

        //取整
        BigDecimal bignum4 = new BigDecimal("59.75");
        long up = bignum4.setScale(0, BigDecimal.ROUND_UP).longValue(); // 向上取整
        System.out.println("向上取整  是：" + up);

        long down = bignum4.setScale(0, BigDecimal.ROUND_DOWN).longValue(); // 向下取整
        System.out.println("向下取整  是：" + down);

        BigDecimal bignum6 = new BigDecimal("18.70");
        BigDecimal divide0 = bignum6.divide(new BigDecimal(5.00), 1, BigDecimal.ROUND_UP);
        System.out.println("divide0 =" + divide0);

        BigDecimal divide1 = bignum6.divide(new BigDecimal(5.00), 1, BigDecimal.ROUND_DOWN);
        System.out.println("divide1 =" + divide1);

        BigDecimal divide2 = bignum6.divide(new BigDecimal(5.00), 1, BigDecimal.ROUND_HALF_UP);
        System.out.println("divide2 =" + divide2);

        BigDecimal divide3 = bignum6.divide(new BigDecimal(5.00), 1, BigDecimal.ROUND_HALF_DOWN);
        System.out.println("divide3 =" + divide3);


        BigDecimal amt = new BigDecimal(14);
        BigDecimal[] results = amt.divideAndRemainder(BigDecimal.valueOf(5.00));
        System.out.println("results[0]====" + results[0]);

        if (results[1].compareTo(BigDecimal.ZERO) == 1) {
            results[0] = results[0].add(new BigDecimal("1"));
            System.out.println("results[0]加1后====" + results[0]);
        }

        BigDecimal eleFee = new BigDecimal(0.2);
        BigDecimal totalEleFee = results[0].multiply(eleFee).setScale(2, BigDecimal.ROUND_HALF_UP);
        ;
        System.out.println("totalEleFee====" + totalEleFee);

        System.out.println("results[1]===" + results[1]);
        int i = results[1].compareTo(BigDecimal.ZERO);
        System.out.println("i===" + i);

        BigDecimal a = new BigDecimal(-3);
        BigDecimal b = new BigDecimal(1.5);
        BigDecimal totalMoney = new BigDecimal("12.49").setScale(0, BigDecimal.ROUND_DOWN);
        //BigDecimal totalMoney = a.add(b).setScale(0,BigDecimal.ROUND_UP);
        System.out.println(totalMoney);


        BigDecimal number = new BigDecimal(0);
        int value = 1;
        number = BigDecimal.valueOf(value);
        System.out.println("number===" + number);
    }
}
