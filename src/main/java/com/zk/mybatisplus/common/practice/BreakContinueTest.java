package com.zk.mybatisplus.common.practice;

public class BreakContinueTest {
    public static void main(String[] args) {
        BreakContinueTest test = new BreakContinueTest();
        test.testBreak1();
        test.testContinue1();
        test.testBreak2();
        test.testContinue2();
    }

    //break用来结束整个循环体
    public void testBreak1() {
        System.out.println("--------测试break1-------");
        for (int i = 1; i <= 7; i++) {
            if (i == 3) break;
            System.out.println("i=" + i);
        }
    }

    /**
     * 测试带标签的break语句
     * 标签只能写在循环体之前，顺便学习一下java中语句标签的定义和使用
     */
    public void testBreak2() {
        System.out.println("--------测试break2-------");
        int i = 1;
        int k = 4;
        for (; i <= 7; i++, k--) {
            if (k == 0) break;
            System.out.println("i=" + i + " ; k=" + k);
        }
    }

    /**
     * continue用来结束本次循环
     */
    public void testContinue1() {
        System.out.println("--------测试continue-------");
        for (int i = 1; i <= 7; i++) {
            if (i % 3 == 0) continue;
            System.out.println("i=" + i);
        }
    }

    public void testContinue2() {
        System.out.println("--------测试continue2-------");
        for (int i = 1; i < 10; i++) {
            System.out.println("i=" + i);
            for (int j = 0; j < 10; j++) {
                if (j == 9) continue;
            }
        }
    }

}
