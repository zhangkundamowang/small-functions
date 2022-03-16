package com.zk.mybatisplus.common.practice;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;

public class JsonTest {
    public static void main(String[] args) {
        String jsonStr = "{\n" +
                "\t\"code\": \"WaypointMission\",\n" +
                "\t\"data\": {\n" +
                "\t\t\"exitMissionOnRCSignalLostEnabled\": false,\n" +
                "\t\t\"missionID\": 0,\n" +
                "\t\t\"gimbalElevationOptimizeEnabled\": false,\n" +
                "\t\t\"finishedAction\": \"GO_HOME\",\n" +
                "\t\t\"flightPathMode\": \"NORMAL\",\n" +
                "\t\t\"waypointList\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"altitude\": 30.0,\n" +
                "\t\t\t\t\"latitude\": 22.000269796599999,\n" +
                "\t\t\t\t\"longitude\": 113.0,\n" +
                "\t\t\t\t\"gimbalPitch\": 0.0,\n" +
                "\t\t\t\t\"actionRepeatTimes\": 1,\n" +
                "\t\t\t\t\"heading\": 0,\n" +
                "\t\t\t\t\"isUseCustomDirection\": false,\n" +
                "\t\t\t\t\"shootPhotoDistanceInterval\": 0.0,\n" +
                "\t\t\t\t\"actionTimeoutInSeconds\": 999,\n" +
                "\t\t\t\t\"headingInner\": 0,\n" +
                "\t\t\t\t\"speed\": 0.0,\n" +
                "\t\t\t\t\"shootPhotoTimeInterval\": 0.0,\n" +
                "\t\t\t\t\"cornerRadiusInMeters\": 0.2,\n" +
                "\t\t\t\t\"turnMode\": \"COUNTER_CLOCKWISE\",\n" +
                "\t\t\t\t\"waypointActions\": [\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"actionType\": \"ROTATE_AIRCRAFT\",\n" +
                "\t\t\t\t\t\t\"actionParam\": 135\n" +
                "\t\t\t\t\t} \n" +
                "\t\t\t\t]\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"gotoFirstWaypointMode\": \"SAFELY\",\n" +
                "\t\t\"repeatTimes\": 1,\n" +
                "\t\t\"latitude\": 22.0,\n" +
                "\t\t\"longitude\": 113.0,\n" +
                "\t\t\"gimbalPitchRotationEnabled\": true,\n" +
                "\t\t\"maxFlightSpeed\": 10.0,\n" +
                "\t\t\"headingMode\": \"TOWARD_POINT_OF_INTEREST\",\n" +
                "\t\t\"autoFlightSpeed\": 5.0\n" +
                "\t}\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        String code=jsonObject.getString("code");
        Map<String,Object> map=(Map<String,Object>)jsonObject.get("data");

        System.out.println("code"+code  );
        System.out.println(map);

    }
}
