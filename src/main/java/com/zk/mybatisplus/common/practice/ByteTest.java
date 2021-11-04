package com.zk.mybatisplus.common.practice;

public class ByteTest {
    public static void main(String[] args) {
        byte[] byte1 = {0x01, 0x06, 0x00, 0x01};
        byte[] byte2 = {0x00, 0x01};
        byte[] bytes = mergeBytes(byte1, byte2);
        System.out.println(bytes);
        System.out.println(bytes.length);
    }

    /**
     * 合并两个byte数组
     *
     * @return 合并后的新数组
     */
    public static byte[] mergeBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;
    }
}
