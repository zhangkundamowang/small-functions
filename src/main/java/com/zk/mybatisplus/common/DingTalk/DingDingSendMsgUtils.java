package com.zk.mybatisplus.common.DingTalk;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import java.util.List;
import java.util.Map;

/**
 * Java实现钉钉机器人推送消息
 */
public class DingDingSendMsgUtils {

    /**
     * 超时时间
     */
    private static final int timeout = 10000;

    /**
     * 每个群开通的自定义机器人有webhook，后期可更换或写在配置文件作为参数传入
     */
    private static final String webhook = "https://oapi.dingtalk.com/robot/send?access_token=6b459c9df3cccbc9cc2bf2df7de4838b816c5d78028620572001c570c9265da8";

    /**
     * 自定义关键词，安全设置，后期可更换或写在配置文件作为参数传入
     */
    private static final String CUSTOM_KEYWORDS = "告警信息:";

    /**
     * 提示@所有人
     *
     */
    public static void sendMessageAtAll(String msg) {
        Map<String, Object> atMap = MapUtil.newHashMap();
        atMap.put("isAtAll", true);
        String reqString = getString(msg, atMap);
        HttpRequest.post(webhook).body(reqString).timeout(timeout).execute().body();
    }

    /**
     * 提示@具体选择的某个人
     *
     * @param msg        通知消息
     * @param mobileList 通知人的电话号码
     */
    public static void sendMessageAtChosePerson(String msg, List<String> mobileList) {
        Map<String, Object> atMap = MapUtil.newHashMap();
        atMap.put("isAtAll", false);
        atMap.put("atMobiles", mobileList);
        String reqString = getString(msg, atMap);
        HttpRequest.post(webhook).body(reqString).timeout(timeout).execute().body();
    }


    public static String getString(String msg, Map<String, Object> atMap) {
        String content = CUSTOM_KEYWORDS + msg;
        Map<String, String> contentMap = MapUtil.newHashMap();
        contentMap.put("content", content);
        Map<String, Object> reqMap = MapUtil.newHashMap();
        reqMap.put("msgtype", "text");
        reqMap.put("text", contentMap);
        reqMap.put("at", atMap);
        String reqString = JSON.toJSONString(reqMap);
        return reqString;
    }

}

