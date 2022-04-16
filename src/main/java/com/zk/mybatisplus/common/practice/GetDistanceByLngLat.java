package com.zk.mybatisplus.common.practice;

import com.zk.mybatisplus.common.utils.MapUtils;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

/**
 * JAVA计算两个经纬度之间的距离
 * 经度范围是0-180°,纬度范围是0-90°
 */
public class GetDistanceByLngLat {

    public static void main(String[] args) {

        System.out.println("调用MapUtils方法-----" + MapUtils.getDistance(29.490295, 106.486654, 29.615467, 106.581515));

        System.out.println("直接调用本类中getDistance方法----" + getDistance(106.486654, 29.490295, 106.581515, 29.615467));

        /**
         * 对比百度地图，计算结果和Sphere坐标系计算结果一致，表明计算结果正确，
         * WGS84坐标系的计算结果存在几十米的误差。
         * 不同的坐标系精度不同，计算结果不一样。大家根据实际情况自己选择。
         */
        GlobalCoordinates source = new GlobalCoordinates(29.490295, 106.486654);
        GlobalCoordinates target = new GlobalCoordinates(29.615467, 106.581515);
        double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
        double meter2 = getDistanceMeter(source, target, Ellipsoid.WGS84);
        System.out.println("Sphere坐标系计算结果：" + meter1 + "米");
        System.out.println("WGS84坐标系计算结果：" + meter2 + "米");

    }

    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid) {
        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);
        return geoCurve.getEllipsoidalDistance();
    }

    /**
     * 返回单位为M
     * lng 经度  lat 纬度
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        return (111120 * 1 / 0.017453292) * Math.acos((Math.sin(lat1 * 0.017453292) * Math.sin(lat2 * 0.017453292)) + ((Math.cos(lat1 * 0.017453292) * Math.cos(lat2 * 0.017453292)) * Math.cos(lng2 * 0.017453292 - lng1 * 0.017453292)));
    }

}
