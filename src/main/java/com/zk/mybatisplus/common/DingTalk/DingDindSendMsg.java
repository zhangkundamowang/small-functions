package com.zk.mybatisplus.common.DingTalk;

import com.zk.mybatisplus.common.utils.DingDingSendMsgUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * @全体成员发送 根据手机号@群成员发送 不@任何人纯发送告警消息
 */
public class DingDindSendMsg {
    public static void main(String[] args) {
         DingDingSendMsgUtils.sendMessageAtAll("测试艾特全体成员");

        List<String> mobileList=new ArrayList<>();
        mobileList.add("18056250973");
        DingDingSendMsgUtils.sendMessageAtChosePerson("测试根据手机号@成员",mobileList);
    }
}
