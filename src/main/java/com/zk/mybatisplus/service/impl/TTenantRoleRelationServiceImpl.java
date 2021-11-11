//package com.zk.mybatisplus.service.impl;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.zk.mybatisplus.common.utils.HttpUtil;
//import com.zk.mybatisplus.model.TTenantRoleRelation;
//import com.zk.mybatisplus.mapper.TTenantRoleRelationMapper;
//import com.zk.mybatisplus.service.TTenantRoleRelationService;
//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
//import org.springframework.stereotype.Service;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author zk
// * @since 2021-09-08
// */
//@Service
//public class TTenantRoleRelationServiceImpl extends ServiceImpl<TTenantRoleRelationMapper, TTenantRoleRelation> implements TTenantRoleRelationService {
//
//    //百度地图 应用AK
//    private static final String ak = "x5oankmSoKM9XEZsbtWPfE7aabnojtI9";
//
//    //百度地图 驾车路线规划api
//    private static final String url = "https://api.map.baidu.com/directionlite/v1/driving?origin=";
//
//    @Override
//    public Map<Object, Object> getDirection(String origin, String destination) {
//        Map<Object, Object> map = new HashMap<>();
//        String s = HttpUtil.doGet(url + origin + "&destination=" + destination + "&ak=" + ak);
//        System.out.println("响应结果为-------------------" + s);
//        JSONObject json = JSON.parseObject(JSON.parseArray(JSON.parseObject(JSON.parseObject(s).get("result").toString()).get("routes").toString()).get(0).toString());
//        // 耗时 单位 秒
//        String duration = json.get("duration").toString();
//        // 秒转换成分钟
//        int num = Integer.valueOf(duration) / 60;
//        // 距离 单位 米
//        String distance = json.get("distance").toString();
//
//        map.put("duration", num + 1); // 避免等于0
//        map.put("distance", distance);
//
//        System.out.println("两点之间距离为: " + distance + "米");
//        System.out.println("两点之间驾车时间为: " + num + "分钟");
//        return map;
//    }
//
//
//}
