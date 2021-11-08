package com.zk.mybatisplus.common.utils;

import org.apache.commons.lang3.StringUtils;

public class MessageBodyUtil {

    /**
     * 1.2.14
     * m_version 主版本和次版本号
     * u_version 修正版本号
     */
    private static final String m_version = "12";
    private static final String u_version = "14";

    /**
     * 返回消息拼装
     *
     * @param header 消息头
     * @param body   消息体
     * @return
     */
    public static byte[] messageReturn(byte[] header, byte[] body) {

        byte[] temp = null;
        if (body == null) {
            byte[] checkCode = ConversionUtil.xorToByteArray(header);
            temp = ConversionUtil.mergeBytes(header, checkCode);
        } else {
            header[6] = Integer.valueOf(body.length).byteValue();
            byte[] checkCode = ConversionUtil.xorToByteArray(ConversionUtil.mergeBytes(header, body));
            temp = ConversionUtil.byteMergerAll(header, checkCode, body);
        }
        return temp;
    }

    /**
     * 消息头的拼装
     *
     * @param code,version,attributes,serialNumber
     * @return
     */
    public static byte[] header(byte[] code, String serialNumber) {
        String version = "0101";
        String attributes = "0101";
        byte[] header = new byte[10];

        //标识位（ASCII码0x43，0x50, 字符串CP）
        header[0] = 0x43;
        header[1] = 0x50;

        //协议版本（修正版本号占用第一个字节，主版本和次版本号采用BCD码，共同占用第二个字节）
        header[2] = ConversionUtil.str2Bcd(m_version)[0];
        header[3] = ConversionUtil.str2Bcd(u_version)[0];

        //消息id
        header[4] = code[0];
        header[5] = code[1];

        //消息体属性
        header[6] = 0x00;
        header[7] = 0x00;

        //消息流水号
        byte[] serialNumberByte = ConversionUtil.strHexByte(serialNumber);
        header[8] = serialNumberByte[0];
        header[9] = serialNumberByte[1];
        return header;
    }

    /**
     * 时间格式解析，输出格式"yyyyMMddHHmmss"
     *
     * @return
     */
    public static String timeParsing(byte[] number) {
        String exchangeByte = "";
        if (number.length >= 7) {
            exchangeByte = tenByte(ConversionUtil.byteExchange(ConversionUtil.subBytes(number, 0, 2)))+"-"
                    + tenByte(ConversionUtil.subBytes(number, 2, 1))+"-"
                    + tenByte(ConversionUtil.subBytes(number, 3, 1))+" "
                    + tenByte(ConversionUtil.subBytes(number, 4, 1))+":"
                    + tenByte(ConversionUtil.subBytes(number, 5, 1))+":"
                    + tenByte(ConversionUtil.subBytes(number, 6, 1));
            //年（2字节）+月（1字节）+日（1字节）+时（1字节）+分（1字节）+秒（1字节）
            return exchangeByte;
        } else {
            return null;
        }
    }

    public static String tenByte(byte[] number) {
        String message = "";
        int length = number.length;
        message = ConversionUtil.binary(number, 10);
        if (message.length() < length * 2) {
            int difference = length * 2 - message.length();
            for (int i = 0; i < difference; i++) {
                message = "0" + message;
            }
        }
        return message;
    }

    /**
     * 时间格式拼装，输入格式"yyyy-MM-dd HH:mm:ss"或"yyyyMMddHHmmss"，输出为8字节数组
     *
     * @return
     */
    public static byte[] timeAssemble(String str) {
        byte[] exchangeByte;
        if (str.length() == 19) {//格式校验，"yyyy-MM-dd HH:mm:ss"
            String yearSixteen = StringUtils.substring(str, 0, 4);
            String monthSixteen = StringUtils.substring(str, 5, 7);
            String daySixteen = StringUtils.substring(str, 8, 10);
            String hourSixteen = StringUtils.substring(str, 11, 13);
            String minuteSixteen = StringUtils.substring(str, 14, 16);
            String secondSixteen = StringUtils.substring(str, 17);

            exchangeByte = ConversionUtil.byteMergerAll(
                    ConversionUtil.byteExchange(ConversionUtil.strHexByte(yearSixteen)),
                    ConversionUtil.strHexByte(monthSixteen),
                    ConversionUtil.strHexByte(daySixteen),
                    ConversionUtil.strHexByte(hourSixteen),
                    ConversionUtil.strHexByte(minuteSixteen),
                    ConversionUtil.strHexByte(secondSixteen));
            //年（2字节，低位在前）+月（1字节）+日（1字节）+时（1字节）+分（1字节）+秒（1字节）
            return exchangeByte;
        } else if (str.length() == 14) {//格式校验，"yyyyMMddHHmmss"
            String yearSixteen = StringUtils.substring(str, 0, 4);
            String monthSixteen = StringUtils.substring(str, 4, 6);
            String daySixteen = StringUtils.substring(str, 6, 8);
            String hourSixteen = StringUtils.substring(str, 8, 10);
            String minuteSixteen = StringUtils.substring(str, 10, 12);
            String secondSixteen = StringUtils.substring(str, 12);

            exchangeByte = ConversionUtil.byteMergerAll(
                    ConversionUtil.byteExchange(ConversionUtil.strHexByte(yearSixteen)),
                    ConversionUtil.strHexByte(monthSixteen),
                    ConversionUtil.strHexByte(daySixteen),
                    ConversionUtil.strHexByte(hourSixteen),
                    ConversionUtil.strHexByte(minuteSixteen),
                    ConversionUtil.strHexByte(secondSixteen));
            //年（2字节，低位在前）+月（1字节）+日（1字节）+时（1字节）+分（1字节）+秒（1字节）
            return exchangeByte;
        } else {
            return null;
        }
    }

    public static String sixteenStr(String str, int num) {
        String sixteenStr = ConversionUtil.intToHex(Integer.parseInt(str));//转成16进制
        int difference = num - sixteenStr.length();
        if (difference == 0) {//等于要转的长度
            return sixteenStr;
        } else if (difference > 0) {//小于要转的位数，补0
            for (int i = 0; i < difference; i++) {
                sixteenStr = "0" + sixteenStr;
            }
            return sixteenStr;
        } else {//错误
            return null;
        }
    }
}