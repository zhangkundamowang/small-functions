package com.zk.mybatisplus.common.practice;

public class Date {
    public static void main(String[] args) {
        // 1) 创建java.util.Date的对象
        java.util.Date utilDate1 = new java.util.Date();
        System.out.println("utilDate1---"+utilDate1);

        // 2)将java.util.Date转成java.sql.Date
        long datems  = utilDate1.getTime();
        java.sql.Date  sqlDate = new java.sql.Date(datems);
        System.out.println("sqlDate---"+sqlDate);

        // 3)将 java.sql.Date 转成 java.util.Date
        long datems2 = sqlDate.getTime();
        java.util.Date utilDate2 = new java.util.Date(datems2);
        System.out.println("utilDate2---"+utilDate2);

        //4)java.sql.Date自动转型java.util.Date
        java.util.Date utilDate3 = sqlDate;
        System.out.println("utilDate3---"+utilDate3);
    }
}
