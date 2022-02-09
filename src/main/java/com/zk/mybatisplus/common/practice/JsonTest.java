package com.zk.mybatisplus.common.practice;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;

public class JsonTest {
    public static void main(String[] args) {
        String jsonStr = "{\n" +
                "     \"code\": \"FlightControllerState\",\n" +
                "    \"nestId\": \"UAV0001\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"activeBrakeEngaged\": false,\n" +
                "\t\t\"aircraftHeadDirection\": 127,\n" +
                "\t\t\"aircraftLocation\": {\n" +
                "\t\t\t\"altitude\": 0.0,\n" +
                "\t\t\t\"latitude\": 0.0,\n" +
                "\t\t\t\"longitude\": 0.0\n" +
                "\t\t},\n" +
                "\t\t\"doesUltrasonicHaveError\": false,\n" +
                "\t\t\"failsafeEnabled\": false,\n" +
                "\t\t\"flightCount\": 0,\n" +
                "\t\t\"flightLogIndex\": 0,\n" +
                "\t\t\"flightMode\": \"GPS_ATTI\",\n" +
                "\t\t\"flightTimeInSeconds\": 0,\n" +
                "\t\t\"flying\": false,\n" +
                "\t\t\"goHomeHeight\": 0,\n" +
                "\t\t\"goingHome\": false,\n" +
                "\t\t\"hasReachedMaxFlightHeight\": false,\n" +
                "\t\t\"hasReachedMaxFlightRadius\": false,\n" +
                "\t\t\"homeLocationSet\": false,\n" +
                "\t\t\"iMUPreheating\": false,\n" +
                "\t\t\"islandingConfirmationNeeded\": false,\n" +
                "\t\t\"lowerThanBatteryWarningThreshold\": false,\n" +
                "\t\t\"lowerThanSeriousBatteryWarningThreshold\": false,\n" +
                "\t\t\"motorsOn\": false,\n" +
                "\t\t\"multipModeOpen\": false,\n" +
                "\t\t\"satelliteCount\": 0,\n" +
                "\t\t\"takeoffLocationAltitude\": 0.0,\n" +
                "\t\t\"ultrasonicBeingUsed\": false,\n" +
                "\t\t\"ultrasonicHeightInMeters\": 0.0,\n" +
                "\t\t\"velocityX\": 0.0,\n" +
                "\t\t\"velocityY\": 0.0,\n" +
                "\t\t\"velocityZ\": 0.0,\n" +
                "\t\t\"visionPositioningSensorBeingUsed\": false\n" +
                "\t}\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Map<String, Object> data = (Map<String, Object>) jsonObject.get("data");
        String activeBrakeEngaged = data.get("activeBrakeEngaged").toString();
        System.out.println(activeBrakeEngaged + "===");
        Map<String, Object> a = (Map<String, Object>) data.get("aircraftLocation");

        System.out.println(a + "---");
        System.out.println(a.get("altitude".toString()));

    }
}
