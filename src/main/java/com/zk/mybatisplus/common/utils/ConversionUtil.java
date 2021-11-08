package com.zk.mybatisplus.common.utils;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class ConversionUtil {
    /**
     * // 开始读取指令
     *
     * @return
     */
    public static byte[] getReadDataCmd() {
        byte[] byteArray = new byte[6];//55 AA 02 25 01 26
        byteArray[0] = 0x55;
        byteArray[1] = (byte) 0xAA;
        byteArray[2] = 0x02;
        byteArray[3] = 0x25;
        byteArray[4] = 0x01;
        byteArray[5] = 0x26;
        return byteArray;
    }

    /**
     * // 指定位数随机数
     *
     * @return
     */
    public static String getRandom(int len) {
        Random r = new Random();
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < len; i++) {
            rs.append(r.nextInt(10));
        }
        return rs.toString();
    }

    /**
     * 获取crc校验字符串
     */
    public static String getCRC2(byte[] bytes) {
//        ModBus 通信协议的 CRC ( 冗余循环校验码含2个字节, 即 16 位二进制数。
//        CRC 码由发送设备计算, 放置于所发送信息帧的尾部。
//        接收信息设备再重新计算所接收信息 (除 CRC 之外的部分）的 CRC,
//        比较计算得到的 CRC 是否与接收到CRC相符, 如果两者不相符, 则认为数据出错。
//
//        1) 预置 1 个 16 位的寄存器为十六进制FFFF(即全为 1) , 称此寄存器为 CRC寄存器。
//        2) 把第一个 8 位二进制数据 (通信信息帧的第一个字节) 与 16 位的 CRC寄存器的低 8 位相异或, 把结果放于 CRC寄存器。
//        3) 把 CRC 寄存器的内容右移一位( 朝低位)用 0 填补最高位, 并检查右移后的移出位。
//        4) 如果移出位为 0, 重复第 3 步 ( 再次右移一位); 如果移出位为 1, CRC 寄存器与多项式A001 ( 1010 0000 0000 0001) 进行异或。
//        5) 重复步骤 3 和步骤 4, 直到右移 8 次,这样整个8位数据全部进行了处理。
//        6) 重复步骤 2 到步骤 5, 进行通信信息帧下一个字节的处理。
//        7) 将该通信信息帧所有字节按上述步骤计算完成后,得到的16位CRC寄存器的高、低字节进行交换。
//        8) 最后得到的 CRC寄存器内容即为 CRC码。

        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        //高低位转换，看情况使用（譬如本人这次对led彩屏的通讯开发就规定校验码高位在前低位在后，也就不需要转换高低位)
        //CRC = ( (CRC & 0x0000FF00) >> 8) | ( (CRC & 0x000000FF ) << 8);
        return Integer.toHexString(CRC);
    }

    /**
     * 获取crc校验字节数组
     */
    public static byte[] CRC2ToByte(byte[] bytes) {
        return ConversionUtil.strHexByte(getCRC3(bytes));

    }

    /**
     * 查表法计算CRC16校验
     *
     * @param data 需要计算的字节数组
     */
    public static String getCRC3(byte[] data) {
        byte[] crc16_h = {
                (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40,
                (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41,
                (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41,
                (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40,
                (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41,
                (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40,
                (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40,
                (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41,
                (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41,
                (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40,
                (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40,
                (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41,
                (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40,
                (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41,
                (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41,
                (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x01, (byte) 0xC0, (byte) 0x80, (byte) 0x41, (byte) 0x00, (byte) 0xC1, (byte) 0x81, (byte) 0x40
        };

        byte[] crc16_l = {
                (byte) 0x00, (byte) 0xC0, (byte) 0xC1, (byte) 0x01, (byte) 0xC3, (byte) 0x03, (byte) 0x02, (byte) 0xC2, (byte) 0xC6, (byte) 0x06, (byte) 0x07, (byte) 0xC7, (byte) 0x05, (byte) 0xC5, (byte) 0xC4, (byte) 0x04,
                (byte) 0xCC, (byte) 0x0C, (byte) 0x0D, (byte) 0xCD, (byte) 0x0F, (byte) 0xCF, (byte) 0xCE, (byte) 0x0E, (byte) 0x0A, (byte) 0xCA, (byte) 0xCB, (byte) 0x0B, (byte) 0xC9, (byte) 0x09, (byte) 0x08, (byte) 0xC8,
                (byte) 0xD8, (byte) 0x18, (byte) 0x19, (byte) 0xD9, (byte) 0x1B, (byte) 0xDB, (byte) 0xDA, (byte) 0x1A, (byte) 0x1E, (byte) 0xDE, (byte) 0xDF, (byte) 0x1F, (byte) 0xDD, (byte) 0x1D, (byte) 0x1C, (byte) 0xDC,
                (byte) 0x14, (byte) 0xD4, (byte) 0xD5, (byte) 0x15, (byte) 0xD7, (byte) 0x17, (byte) 0x16, (byte) 0xD6, (byte) 0xD2, (byte) 0x12, (byte) 0x13, (byte) 0xD3, (byte) 0x11, (byte) 0xD1, (byte) 0xD0, (byte) 0x10,
                (byte) 0xF0, (byte) 0x30, (byte) 0x31, (byte) 0xF1, (byte) 0x33, (byte) 0xF3, (byte) 0xF2, (byte) 0x32, (byte) 0x36, (byte) 0xF6, (byte) 0xF7, (byte) 0x37, (byte) 0xF5, (byte) 0x35, (byte) 0x34, (byte) 0xF4,
                (byte) 0x3C, (byte) 0xFC, (byte) 0xFD, (byte) 0x3D, (byte) 0xFF, (byte) 0x3F, (byte) 0x3E, (byte) 0xFE, (byte) 0xFA, (byte) 0x3A, (byte) 0x3B, (byte) 0xFB, (byte) 0x39, (byte) 0xF9, (byte) 0xF8, (byte) 0x38,
                (byte) 0x28, (byte) 0xE8, (byte) 0xE9, (byte) 0x29, (byte) 0xEB, (byte) 0x2B, (byte) 0x2A, (byte) 0xEA, (byte) 0xEE, (byte) 0x2E, (byte) 0x2F, (byte) 0xEF, (byte) 0x2D, (byte) 0xED, (byte) 0xEC, (byte) 0x2C,
                (byte) 0xE4, (byte) 0x24, (byte) 0x25, (byte) 0xE5, (byte) 0x27, (byte) 0xE7, (byte) 0xE6, (byte) 0x26, (byte) 0x22, (byte) 0xE2, (byte) 0xE3, (byte) 0x23, (byte) 0xE1, (byte) 0x21, (byte) 0x20, (byte) 0xE0,
                (byte) 0xA0, (byte) 0x60, (byte) 0x61, (byte) 0xA1, (byte) 0x63, (byte) 0xA3, (byte) 0xA2, (byte) 0x62, (byte) 0x66, (byte) 0xA6, (byte) 0xA7, (byte) 0x67, (byte) 0xA5, (byte) 0x65, (byte) 0x64, (byte) 0xA4,
                (byte) 0x6C, (byte) 0xAC, (byte) 0xAD, (byte) 0x6D, (byte) 0xAF, (byte) 0x6F, (byte) 0x6E, (byte) 0xAE, (byte) 0xAA, (byte) 0x6A, (byte) 0x6B, (byte) 0xAB, (byte) 0x69, (byte) 0xA9, (byte) 0xA8, (byte) 0x68,
                (byte) 0x78, (byte) 0xB8, (byte) 0xB9, (byte) 0x79, (byte) 0xBB, (byte) 0x7B, (byte) 0x7A, (byte) 0xBA, (byte) 0xBE, (byte) 0x7E, (byte) 0x7F, (byte) 0xBF, (byte) 0x7D, (byte) 0xBD, (byte) 0xBC, (byte) 0x7C,
                (byte) 0xB4, (byte) 0x74, (byte) 0x75, (byte) 0xB5, (byte) 0x77, (byte) 0xB7, (byte) 0xB6, (byte) 0x76, (byte) 0x72, (byte) 0xB2, (byte) 0xB3, (byte) 0x73, (byte) 0xB1, (byte) 0x71, (byte) 0x70, (byte) 0xB0,
                (byte) 0x50, (byte) 0x90, (byte) 0x91, (byte) 0x51, (byte) 0x93, (byte) 0x53, (byte) 0x52, (byte) 0x92, (byte) 0x96, (byte) 0x56, (byte) 0x57, (byte) 0x97, (byte) 0x55, (byte) 0x95, (byte) 0x94, (byte) 0x54,
                (byte) 0x9C, (byte) 0x5C, (byte) 0x5D, (byte) 0x9D, (byte) 0x5F, (byte) 0x9F, (byte) 0x9E, (byte) 0x5E, (byte) 0x5A, (byte) 0x9A, (byte) 0x9B, (byte) 0x5B, (byte) 0x99, (byte) 0x59, (byte) 0x58, (byte) 0x98,
                (byte) 0x88, (byte) 0x48, (byte) 0x49, (byte) 0x89, (byte) 0x4B, (byte) 0x8B, (byte) 0x8A, (byte) 0x4A, (byte) 0x4E, (byte) 0x8E, (byte) 0x8F, (byte) 0x4F, (byte) 0x8D, (byte) 0x4D, (byte) 0x4C, (byte) 0x8C,
                (byte) 0x44, (byte) 0x84, (byte) 0x85, (byte) 0x45, (byte) 0x87, (byte) 0x47, (byte) 0x46, (byte) 0x86, (byte) 0x82, (byte) 0x42, (byte) 0x43, (byte) 0x83, (byte) 0x41, (byte) 0x81, (byte) 0x80, (byte) 0x40
        };

        int crc = 0x0000ffff;
        int ucCRCHi = 0x00ff;
        int ucCRCLo = 0x00ff;
        int iIndex;
        for (int i = 0; i < data.length; ++i) {
            iIndex = (ucCRCLo ^ data[i]) & 0x00ff;
            ucCRCLo = ucCRCHi ^ crc16_h[iIndex];
            ucCRCHi = crc16_l[iIndex];
        }

        crc = ((ucCRCHi & 0x00ff) << 8) | (ucCRCLo & 0x00ff) & 0xffff;
        //高低位互换，输出符合相关工具对Modbus CRC16的运算
        //crc = ( (crc & 0xFF00) >> 8) | ( (crc & 0x00FF ) << 8);
        return String.format("%04X", crc);
    }

    /**
     * // 校验和
     *
     * @return
     */
    public static Integer hexString(byte[] hex) {
        Integer hexTwo = 0;
        for (int i = 0; i < hex.length; i++) {
            hexTwo = hexTwo + Integer.valueOf(binary(subBytes(hex, i, 1), 10));
        }
        return hexTwo;
    }

    /**
     * ascll字节数组转字符串
     *
     * @return
     */
    public static String ascllToStr(byte[] hex) {
        String nRcvString;
        StringBuffer tStringBuf = new StringBuffer();
        char[] tChars = new char[hex.length];
        for (int i = 0; i < hex.length; i++)
            tChars[i] = (char) hex[i];

        tStringBuf.append(tChars);
        nRcvString = tStringBuf.toString();
        return nRcvString;
    }

    /**
     * 字符串补0
     *
     * @return
     */
    public static String strFormat(String hex, Integer len) {
        Integer dif = len - hex.length();
        String hexRes = hex;
        if (dif > 0) {
            for (int i = 0; i < dif; i++) {
                hexRes = "0" + hexRes;
            }
        }
        return hexRes;
    }

    /**
     * 获取为0的字节数组
     *
     * @param num 字节数组大小
     */
    public static byte[] strFormat(int num) {
        byte[] temp = new byte[num];
        for (int i = 0; i < num; i++) {
            temp[i] = 0x00;
        }
        return temp;
    }

    /**
     * 转2进制并倒叙组合
     *
     * @return
     */
    public static String twoDesc(byte[] param) {
        String new_s2 = "";
        for (int i = 0; i < param.length; i++) {
            String test_s = String.format("%0" + 8 + "d", new BigInteger(ConversionUtil.binary(subBytes(param, i, 1), 2)));
            StringBuffer sb = new StringBuffer(test_s);
            new_s2 = new_s2 + sb.reverse().toString();
        }
        return new_s2;
    }

    /**
     * 转BCD
     *
     * @return
     */
    public static String stringConvert(String str, int num) {
        int lenth = str.length();
        int dif = num * 2 - lenth;
        if (dif > 0) {
            for (int i = 0; i < dif; i++) {
                str = str + "0";
            }
            return str;
        } else if (dif == 0) {
            return str;
        } else {
            return null;
        }

    }

    /**
     * 0-FF单字节正数转换
     *
     * @return
     */
    public static String getSixteenString(int number) {
        String codeNum = intToHex(number);
        if (number < 16) {
            codeNum = "0" + codeNum;
        }
        return codeNum;
    }

    /**
     * 高低字节反序
     *
     * @return
     */
    public static byte[] byteExchange(byte[] number) {
        byte[] exchangeByte = new byte[number.length];
        for (int i = 0; i < exchangeByte.length; i++) {
            exchangeByte[i] = number[number.length - i - 1];
        }
        return exchangeByte;
    }

    /**
     * 取 int 类型的低八位或高八位
     *
     * @param i1 源数据
     * @param i2 低八位：1  高八位：2
     * @return
     */
    public static int hilo(int i1, int i2) {
        int result;

        if (i2 == 1) {
            result = i1 & 0xff;
        } else {
            result = (i1 & 0xff00) >> 8;
        }
        return result;
    }

    /**
     * 产生随机ASCLL码（输入位数）
     *
     * @return
     */
    public static String getRandomNumCode(int number) {
        String codeNum = "";
        int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int next = random.nextInt(10000);//目的是产生足够随机的数，避免产生的数字重复率高的问题
            codeNum += numbers[next % 10];
        }
        System.out.println(codeNum);
        return codeNum;
    }

    /**
     * 截取byte数组
     *
     * @param src
     * @param begin
     * @param count
     * @return 小端，有符号
     */
    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }

    /**
     * 将两个byte数组合并为一个
     *
     * @param data1 要合并的数组1
     * @param data2 要合并的数组2
     * @return 合并后的新数组
     */
    public static byte[] mergeBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;
    }

    /**
     * 将多个byte数组合并为一个
     *
     * @param data1 要合并的数组1
     * @param data2 要合并的数组2
     * @return 合并后的新数组
     */
    public static byte[] byteMergerAll(byte[]... values) {
        int length_byte = 0;
        for (int i = 0; i < values.length; i++) {
            length_byte += values[i].length;
        }
        byte[] all_byte = new byte[length_byte];
        int countLength = 0;
        for (int i = 0; i < values.length; i++) {
            byte[] b = values[i];
            System.arraycopy(b, 0, all_byte, countLength, b.length);
            countLength += b.length;
        }
        return all_byte;
    }

    /**
     * 10进制转16进制
     *
     * @param b
     * @return
     */
    public static String intToHex(int n) {
        StringBuffer s = new StringBuffer();
        String a;
        char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        while (n != 0) {
            s = s.append(b[n % 16]);
            n = n / 16;
        }
        a = s.reverse().toString();
        return a;
    }

    /**
     * 字节数组转int
     *
     * @param b
     * @return
     */
    public static int byteArrayToInt(byte[] b) {
        int intValue = 0;
        for (int i = 0; i < b.length; i++) {
            intValue += (b[i] & 0xFF) << (8 * (3 - i));
        }
        return intValue;
    }

    /**
     * 字符串转ascll
     *
     * @param b
     * @return
     */
    public static String stringToAscii(String value) {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i != chars.length - 1) {
                sbu.append((int) chars[i]).append(",");
            } else {
                sbu.append((int) chars[i]);
            }
        }
        return sbu.toString();
    }


    /**
     * 字符串转ascll数组
     *
     * @param b
     * @return
     */
    public static byte[] stringToAsciiByte(String value) {
        char[] chars = value.toCharArray();
        byte[] AscllByte = null;
        if (value.length() > 0) {
            for (int i = 0; i < chars.length; i++) {
                Integer num = (int) chars[i];
                if (i == 0) {
                    AscllByte = strHexByte(MessageBodyUtil.sixteenStr(num.toString(), 2));
                } else {
                    AscllByte = mergeBytes(AscllByte, strHexByte(MessageBodyUtil.sixteenStr(num.toString(), 2)));
                }
            }
        }
        return AscllByte;
    }

    /**
     * 字节数组转不同进制字符串
     *
     * @param bytes
     * @param radix
     * @return
     */
    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    /**
     * 字符串每两位一组转化为数组
     *
     * @param data
     * @return
     */
    public static byte[] strHexByte(String data) {
        if (1 == data.length() % 2)
            return null;
        else {
            byte[] li = new byte[data.length() / 2];
            for (int i = 0; i < data.length(); i += 2) {
                int cp1 = data.codePointAt(i);
                int cp2 = data.codePointAt(i + 1);
                li[i / 2] = (byte) (ascHex(cp1) << 4 | ascHex(cp2));
            }
            return li;
        }
    }

    /**
     * 字符asc码数值转为byte数值
     *
     * @param data
     * @return
     */
    public static int ascHex(int data) {
        int li;
        if (data >= 0X30 && data <= 0X39) {//0-9
            li = data - 0X30;
        } else if (data >= 0X41 && data <= 0X5A) {//A-F
            li = data - 0X37;
        } else if (data >= 0X61 && data <= 0X7A) {//a-f
            li = data - 0X57;
        } else {
            li = data;
        }
        return li;
    }

    /**
     * @功能: BCD码转为10进制串(阿拉伯数据)
     * @参数: BCD码
     * @结果: 10进制串
     */
    public static String bcd2Str(byte[] bytes) {
        StringBuffer temp = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
            temp.append((byte) (bytes[i] & 0x0f));
        }
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp
                .toString().substring(1) : temp.toString();
    }

    /**
     * 把16进制字符串转换成字节数组
     *
     * @param hex
     * @return
     */
    public static byte[] hexStringToByte(String hex) {
        int len = (hex.length() / 2); //除以2是因为十六进制比如a1使用两个字符代表一个byte
        byte[] result = new byte[len];
        char[] achar = hex.toCharArray();
        for (int i = 0; i < len; i++) {
            //乘以2是因为十六进制比如a1使用两个字符代表一个byte,pos代表的是数组的位置
            //第一个16进制数的起始位置是0第二个是2以此类推
            int pos = i * 2;
            //<<4位就是乘以16  比如说十六进制的"11",在这里也就是1*16|1,而其中的"|"或运算就相当于十进制中的加法运算
            //如00010000|00000001结果就是00010001 而00010000就有点类似于十进制中10而00000001相当于十进制中的1，与是其中的或运算就相当于是10+1(此处说法可能不太对，)
            result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789ABCDEF".indexOf(c);
        return b;
    }

    /**
     * 把字节数组转换成16进制字符串
     *
     * @param bArray
     * @return
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 把字节数组转换为对象
     *
     * @param bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static final Object bytesToObject(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        ObjectInputStream oi = new ObjectInputStream(in);
        Object o = oi.readObject();
        oi.close();
        return o;
    }

    /**
     * 把可序列化对象转换成字节数组
     *
     * @param s
     * @return
     * @throws IOException
     */
    public static final byte[] objectToBytes(Serializable s) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream ot = new ObjectOutputStream(out);
        ot.writeObject(s);
        ot.flush();
        ot.close();
        return out.toByteArray();
    }

    public static final Object hexStringToObject(String hex) throws IOException, ClassNotFoundException {
        return bytesToObject(hexStringToByte(hex));
    }

    /**
     * @函数功能: 10进制串转为BCD码
     * @输入参数: 10进制串
     * @输出结果: BCD码
     */
    public static byte[] str2Bcd(String asc) {
        int len = asc.length();
        int mod = len % 2;

        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }

        byte abt[] = new byte[len];
        if (len >= 2) {
            len = len / 2;
        }

        byte bbt[] = new byte[len];
        abt = asc.getBytes();
        int j, k;

        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }

            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }

            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }

    /**
     * MD5加密字符串，返回加密后的16进制字符串
     *
     * @param origin
     * @return
     */
    public static String MD5EncodeToHex(String origin) {
        return bytesToHexString(MD5Encode(origin));
    }

    /**
     * MD5加密字符串，返回加密后的字节数组
     *
     * @param origin
     * @return
     */
    public static byte[] MD5Encode(String origin) {
        return MD5Encode(origin.getBytes());
    }

    /**
     * MD5加密字节数组，返回加密后的字节数组
     *
     * @param bytes
     * @return
     */
    public static byte[] MD5Encode(byte[] bytes) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            return md.digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    /**
     * byte转int
     *
     * @Title: bytesToInt
     * @param: @param src 源数组
     * @param: @param offset 起始偏移量
     * @return: int
     */
    public static int bytesToInt(byte[] src, int offset) {
        int value;
        value = src[offset] & 0xFF;
        value |= ((long) (src[offset + 1] & 0xFF) << 8);
        value |= ((long) (src[offset + 2] & 0xFF) << 16);
        value |= ((long) (src[offset + 3] & 0xFF) << 24);
        return value;
    }

    /**
     * byte转long
     *
     * @Title: bytesToLong
     * @param: @param src 源数组
     * @param: @param offset 起始偏移量
     * @return: long
     */
    public static long bytesToLong(byte[] src, int offset) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value |= ((long) (src[offset + i] & 0xFF) << (8 * i));
        }

        return value;
    }

    /**
     * byte转float
     *
     * @Title: byteToFloat
     * @param: @param src 源数组
     * @param: @param offset 起始偏移量
     * @return: float
     */
    public static float byteToFloat(byte[] src, int offset) {
        int value;
        value = src[offset + 0] & 0xFF;
        value |= ((long) (src[offset + 1] & 0xFF) << 8);
        value |= ((long) (src[offset + 2] & 0xFF) << 16);
        value |= ((long) (src[offset + 3] & 0xFF) << 24);
        return Float.intBitsToFloat(value);
    }

    /**
     * byte转double
     *
     * @Title: byteToDouble
     * @param: @param src 源数组
     * @param: @param offset 起始偏移量
     * @return: double
     */
    public static double byteToDouble(byte[] src, int offset) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value |= ((long) (src[offset + i] & 0xff)) << (8 * i);
        }
        return Double.longBitsToDouble(value);
    }

    /**
     * int转byte
     *
     * @Title: intToBytes
     * @param: @param value
     * @return: byte[]
     */
    public static byte[] intToBytes(int value) {
        byte[] result = new byte[4];
        for (int i = 0; i < 4; i++) {
            result[i] = (byte) ((value >> (i * 8)) & 0xFF);
        }
        return result;
    }

    /**
     * long转byte
     *
     * @Title: longToBytes
     * @param: @param value
     * @return: byte[]
     */
    public static byte[] longToBytes(long value) {
        byte[] result = new byte[8];
        for (int i = 0; i < 8; i++) {
            result[i] = (byte) ((value >> (i * 8)) & 0xFF);
        }
        return result;
    }

    /**
     * float转byte
     *
     * @Title: floatToBytes
     * @param: @param f
     * @return: byte[]
     */
    public static byte[] floatToBytes(float f) {
        int fbit = Float.floatToIntBits(f);
        return intToBytes(fbit);
    }

    /**
     * double转byte
     *
     * @Title: doubleToBytes
     * @param: @param d
     * @return: byte[]
     */
    public static byte[] doubleToBytes(double d) {
        long lbit = Double.doubleToRawLongBits(d);
        return longToBytes(lbit);
    }

    /**
     * 字节数组异或校验
     *
     * @Title: xor
     * @param: byte[]
     * @return: byte
     */
    public static byte xorToByte(byte[] src) {
        byte temp = src[0];
        for (int i = 1; i < src.length; i++) {
            temp ^= src[i];
        }
        return temp;
    }

    /**
     * 字节数组异或校验
     *
     * @Title: xor
     * @param: byte[]
     * @return: byte[]
     */
    public static byte[] xorToByteArray(byte[] src) {
        byte[] temp = new byte[1];
        for (int i = 0; i < src.length; i++) {
            if (i == 0) {
                temp[0] = src[i];
            } else {
                temp[0] = (byte) (temp[0] ^ src[i]);
            }
        }
        return temp;
    }

    /**
     * 去除指定的字节
     *
     * @param: bs     原数组
     * @param: index  截止位
     * @return: byte[]
     */
    public static byte[] deleteAt(byte[] bs, int index) {
        int length = bs.length - 1;
        byte[] ret = new byte[length];
        System.arraycopy(bs, 0, ret, 0, index);
        System.arraycopy(bs, index + 1, ret, index, length - index);
        return ret;
    }
}
