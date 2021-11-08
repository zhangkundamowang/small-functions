package com.zk.mybatisplus.common.utils;

public class LineNumberUtil {

    /**
     * 获取当前行数
     */
    public static int getLineNumber() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        int line = e.getLineNumber();
        return line;
    }
}
