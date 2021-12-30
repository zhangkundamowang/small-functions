package com.zk.mybatisplus.common.DingTalk;

import java.util.HashMap;
import java.util.Map;

/**
 * 不@任何人发送告警消息
 */
public class SendMessage {
    public static void main(String[] args){
        //钉钉的webhook
        String dingDingToken="https://oapi.dingtalk.com/robot/send?access_token=6b459c9df3cccbc9cc2bf2df7de4838b816c5d78028620572001c570c9265da8";
        //请求的JSON数据，这里我用map在工具类里转成json格式
        Map<String,Object> map=new HashMap();
        Map<String,Object> text=new HashMap();
        map.put("msgtype","text");
        //content要包含自定义关键字
        text.put("content","临涣焦化告警：VOCs排放浓度大于上限：61.89");
        map.put("text",text);
        String response = SendHttpUtil.sendPostByMap(dingDingToken, map);
        System.out.println("相应结果："+response);

    }
}