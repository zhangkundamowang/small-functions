package com.zk.mybatisplus.common.practice;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.zk.mybatisplus.model.TFlightLog;
import java.util.Map;

public class BeanUtilTest {
    public static void main(String[] args) {

        String json="{\n" +
                "    \"code\":\"FlightControllerState\",\n" +
                "    \"data\":{\n" +
                "        \"activeBrakeEngaged\":false,\n" +
                "        \"aircraftHeadDirection\":-95,\n" +
                "        \"aircraftLocation\":{\n" +
                "            \"altitude\":0,\n" +
                "            \"latitude\":21.99987712667595,\n" +
                "            \"longitude\":112.99998858614124\n" +
                "        },\n" +
                "        \"doesUltrasonicHaveError\":false,\n" +
                "        \"failsafeEnabled\":false,\n" +
                "        \"flightCount\":9,\n" +
                "        \"flightLogIndex\":83,\n" +
                "        \"flightMode\":\"GPS_ATTI\",\n" +
                "        \"flightTimeInS  econds\":850,\n" +
                "        \"flying\":false,\n" +
                "        \"goHomeHeight\":150,\n" +
                "        \"goingHome\":false,\n" +
                "        \"hasReachedMaxFlightHeight\":false,\n" +
                "        \"hasReachedMaxFlightRadius\":false,\n" +
                "        \"homeLocationSet\":true,\n" +
                "        \"iMUPreheating\":false,\n" +
                "        \"islandingConfirmationNeeded\":false,\n" +
                "        \"lowerThanBatteryWarningThreshold\":false,\n" +
                "        \"lowerThanSeriousBatteryWarningThreshold\":false,\n" +
                "        \"motorsOn\":false,\n" +
                "        \"multipModeOpen\":true,\n" +
                "        \"satelliteCount\":15,\n" +
                "        \"takeoffLocationAltitude\":99.80636,\n" +
                "        \"ultrasonicBeingUsed\":true,\n" +
                "        \"ultrasonicHeightIn  Meters\":0,\n" +
                "        \"velocityX\":0,\n" +
                "        \"velocityY\":0,\n" +
                "        \"velocityZ\":0,\n" +
                "        \"visionPositioningSensorBeingUsed\":false\n" +
                "    },\n" +
                "    \"nestid\":\"1\"\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        Map<String, Object> data = (Map<String, Object>) jsonObject.get("data");
        System.out.println(data+"===data");
        Map<String, Object> s = (Map<String, Object>) data.get("aircraftLocation");
        data.put("aircraftLocationAltitude", s.get("altitude"));
        data.put("aircraftLocationLatitude", s.get("latitude"));
        data.put("aircraftLocationLongtitude", s.get("longitude"));
        TFlightLog flightLog = BeanUtil.mapToBean(data, TFlightLog.class, true);
        System.out.println(flightLog);
    }
}
