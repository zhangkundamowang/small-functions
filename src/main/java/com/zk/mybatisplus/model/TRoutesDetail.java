package com.zk.mybatisplus.model;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author zk
 * @since 2022-01-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class TRoutesDetail extends Model<TRoutesDetail> {

    private static final long serialVersionUID = 1L;

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

    /**
     * 俯仰
     */
    private BigDecimal pitch;

    /**
     * 飞机当前的定向模式
     */
    private String orientationMode;

    /**
     * 滚转
     */
    private BigDecimal roll;

    private String orientation;

    /**
     * 偏航
     */
    private BigDecimal yaw;

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
    private BigDecimal homeLocationLongtitude;

    /**
     * 机巢纬度
     */
    private BigDecimal homeLocationLatitude;

    /**
     * 飞机当前经度
     */
    private BigDecimal aircraftLocationLongtitude;

    /**
     * 飞机当前纬度
     */
    private BigDecimal aircraftLocationLatitude;

    /**
     * 飞机当前海拔
     */
    private BigDecimal aircraftLocationAltitude;

    /**
     * 飞行模式
     */
    private String flightMode;

    /**
     * 回巢助手
     */
    private Long goHomeAssessment;

    /**
     * 估计剩余时间
     */
    private Long remainingFlightTime;

    /**
     * 回巢所需时间
     */
    private Long timeNeededToGoHome;

    /**
     * 原地着陆所需时间
     */
    private String timeNeededToLandFromCurrentHeight;

    /**
     * 回巢所需电量
     */
    private Long batteryPercentageNeededToGoHome;

    /**
     * 原地着陆所需电量
     */
    private Long batteryPercentageNeededToLandFromCurrentHeight;

    /**
     * 最大飞行半径
     */
    private BigDecimal maxRadiusAircraftCanFlyAndGoHome;

    /**
     * 智能返航状态
     */
    private String smartRthState;

    /**
     * 智能返航 (RTH) 的倒计时
     */
    private Long smartRthCountdown;

    /**
     * 飞机是否正在回巢
     */
    private String isGoingHome;

    /**
     * 确定是否开启多种飞行模式
     */
    private String isMultipModeOpen;

    /**
     * 是否已设置机巢点
     */
    private String isHomeLocationSet;

    /**
     * 是否已设置信号丢失安全模式
     */
    private String isFailsafeEnabled;

    /**
     * 电机是否打开
     */
    private String motorsOn;

    /**
     * 是否正在使用超声波传感器
     */
    private String isUltrasonicBeingUsed;

    /**
     * 惯性测量单元预热
     */
    private String isImuPreheating;

    /**
     * 视觉传感器是否启用
     */
    private String isVisionPositioningSensorBeingUsed;

    /**
     * 超声波传感器是否有错误
     */
    private String doesUltrasonicHaveError;

    /**
     * 飞机是否在飞行
     */
    private String isFlying;

    /**
     * 获取飞机返航时的高度（以米为单位）
     */
    private Long goHomeHeight;

    /**
     * 飞机航向
     */
    private String aircraftHeadDirection;

    /**
     * 超声波传感器测量的飞机高度，以米为单位
     */
    private Long ultrasonicHeightInMeters;

    /**
     * 飞机返航位置相对于海平面的相对高度，以米为单位
     */
    private Long takeoffLocationAltitude;

    /**
     * GPS 卫星计数
     */
    private Long satelliteCount;

    /**
     * 基于剩余电量的推荐操作
     */
    private String batteryThresholdBehavior;

    /**
     * 获取速度x
     */
    private Long velocityX;

    /**
     * 获取速度Y
     */
    private Long velocityY;

    /**
     * 获取速度Z
     */
    private Long velocityZ;

    /**
     * 自飞机发动机开启以来的累计飞行时间，以秒为单位。
     */
    private Long flightTimeInSeconds;

    /**
     * 电池电量是否低于低电量警告阈值
     */
    private BigDecimal isLowerThanBatteryWarningThreshold;

    /**
     * 电池电量低于严重低电量警告阈值
     */
    private BigDecimal isLowerThanSeriousBatteryWarningThreshold;

    /**
     * 风速告警
     */
    private String flightWindWarning;

    /**
     * 飞机是否需要着陆确认
     */
    private String isLandingConfirmationNeeded;

    /**
     * 电池生命周期内的飞行次数
     */
    private Long flightCount;

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

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 高度
     */
    private Double highly;

    /**
     * 速度
     */
    private Double speed;

    /**
     * 倾斜飞行速度
     */
    private Double speedTilt;

    private String category;

    /**
     * 创建时间
     */
    private Date createTime;

    private Date updateTime;

    /**
     * 是否可用  Y:可用  N:不可用
     */
    private String valid;

    /**
     * 日志描述(用json表示)
     */
    private String detail;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
