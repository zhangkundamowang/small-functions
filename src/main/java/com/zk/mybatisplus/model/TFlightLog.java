package com.zk.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName(value = "t_flight_log")
public class TFlightLog extends Model<TFlightLog> {

    private static final long serialVersionUID = 1L;
    private Integer id;
    /**
     * 租户账号
     */
    private String tenantAccounts;

    /**
     * 租户名称
     */
    private String companyName;

    private Integer missionId;

    /**
     * 飞行记录id
     */
    private Integer flightRecordId;

    private Integer sequence;

    private Integer droneId;

    private Integer nestId;

    /**
     * 俯仰
     */
    private Double pitch;

    /**
     * 飞机当前的定向模式
     */
    private String orientationMode;

    /**
     * 滚转
     */
    private Double roll;

    private String orientation;

    /**
     * 偏航
     */
    private Double yaw;

    /**
     * 回巢执行的当前状态
     */
    private String goHomeExecutionState;

    /**
     * 获取GPS信号级别
     */
    private String gpsSignalLevel;

    /**
     * 机巢经度
     */
    private Double homeLocationLongtitude;

    /**
     * 机巢纬度
     */
    private Double homeLocationLatitude;

    /**
     * 飞机当前经度
     */
    private Double aircraftLocationLongtitude;

    /**
     * 飞机当前纬度
     */
    private Double aircraftLocationLatitude;

    /**
     * 飞机当前海拔
     */
    private Double aircraftLocationAltitude;

    /**
     * 飞行模式
     */
    private String flightMode;

    /**
     * 回巢助手
     */
    private Integer goHomeAssessment;

    /**
     * 估计剩余时间
     */
    private Double remainingFlightTime;

    /**
     * 回巢所需时间
     */
    private Double timeNeededToGoHome;

    /**
     * 原地着陆所需时间
     */
    private String timeNeededToLandFromCurrentHeight;

    /**
     * 回巢所需电量
     */
    private Double batteryPercentageNeededToGoHome;

    /**
     * 原地着陆所需电量
     */
    private Double batteryPercentageNeededToLandFromCurrentHeight;

    /**
     * 最大飞行半径
     */
    private Double maxRadiusAircraftCanFlyAndGoHome;

    /**
     * 智能返航状态
     */
    private String smartRthState;

    /**
     * 智能返航 (RTH) 的倒计时
     */
    private Integer smartRthCountdown;

    /**
     * 飞机是否正在回巢
     */
    private String goingHome;

    /**
     * 确定是否开启多种飞行模式
     */
    private String multipModeOpen;

    /**
     * 是否已设置机巢点
     */
    private String homeLocationSet;

    /**
     * 是否已设置信号丢失安全模式
     */
    private String failsafeEnabled;

    /**
     * 电机是否打开
     */
    private String motorsOn;

    /**
     * 是否正在使用超声波传感器
     */
    private String ultrasonicBeingUsed;

    /**
     * 惯性测量单元预热
     */
    private String iMUPreheating;

    /**
     * 视觉传感器是否启用
     */
    private String visionPositioningSensorBeingUsed;

    /**
     * 超声波传感器是否有错误
     */
    private String doesUltrasonicHaveError;

    /**
     * 飞机是否在飞行
     */
    private String flying;

    /**
     * 获取飞机返航时的高度（以米为单位）
     */
    private Double goHomeHeight;

    /**
     * 飞机航向
     */
    private String aircraftHeadDirection;

    /**
     * 超声波传感器测量的飞机高度，以米为单位
     */
    private Double ultrasonicHeightInMeters;

    /**
     * 飞机返航位置相对于海平面的相对高度，以米为单位
     */
    private Double takeoffLocationAltitude;

    /**
     * GPS 卫星计数
     */
    private Integer satelliteCount;

    /**
     * 基于剩余电量的推荐操作
     */
    private String batteryThresholdBehavior;

    /**
     * 获取速度x
     */
    private Double velocityX;

    /**
     * 获取速度Y
     */
    private Double velocityY;

    /**
     * 获取速度Z
     */
    private Double velocityZ;

    /**
     * 自飞机发动机开启以来的累计飞行时间，以秒为单位。
     */
    private Double flightTimeInSeconds;

    /**
     * 电池电量是否低于低电量警告阈值
     */
    private Double lowerThanBatteryWarningThreshold;

    /**
     * 电池电量低于严重低电量警告阈值
     */
    private Double lowerThanSeriousBatteryWarningThreshold;

    /**
     * 风速告警
     */
    private String flightWindWarning;

    /**
     * 飞机是否需要着陆确认
     */
    private String islandingConfirmationNeeded;

    /**
     * 电池生命周期内的飞行次数
     */
    private Integer flightCount;

    /**
     * 飞机上飞行日志的当前索引
     */
    private String flightLogIndex;

    /**
     * 是否启用主动制动以避开障碍物时。只有 Matrice 300 RTK 支持
     */
    private String activeBrakeEngaged;

    /**
     * 坐标
     */
    private String coordinates;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
