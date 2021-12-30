package com.zk.mybatisplus.common.practice;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Period.between（）大坑
 */
public class LocalDateTimeTest {
    public static void main(String[] args) {
//        LocalDateTime now = LocalDateTime.now();
//        LocalDate localDate = now.toLocalDate();
//        LocalTime localTime = now.toLocalTime();

//
//        System.out.println(LocalDate.of(2020,12,1));
//        System.out.println(LocalDate.of(2020, Month.APRIL,23));
//        System.out.println(LocalTime.of(12,23,33));
//        System.out.println(LocalDateTime.of(LocalDate.now(),LocalTime.now()));
//        System.out.println(LocalDateTime.of(2322,12,22,23,12,33,232));
//        System.out.println(LocalDateTime.of(2322,Month.JULY,22,23,12,33,232));

//        System.out.println(now);
//        System.out.println(now.plusYears(1)
//                                .plusMonths(1L).plusWeeks(1).plusDays(1).plusHours(1).plusMinutes(1).plusSeconds(1).plusNanos(50000L));

//        LocalDate date=LocalDate.of(2020,2,22);
//        System.out.println(date.withYear(2021));
//        System.out.println(date.withMonth(12));
//        System.out.println(date.withDayOfMonth(1));
//        DayOfWeek dayOfWeek = now.getDayOfWeek();
//        System.out.println(dayOfWeek);
//        System.out.println(now.getHour());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(date);
        System.out.println(dateString);
        String hour;
        hour = dateString.substring(11, 13);
        System.out.println(hour);


    }
}
