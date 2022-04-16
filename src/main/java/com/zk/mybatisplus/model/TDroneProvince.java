package com.zk.mybatisplus.model;

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
    * 省份设置
    * </p>
*
* @author zk
* @since 2022-02-09
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public class TDroneProvince extends Model<TDroneProvince> {

    private static final long serialVersionUID = 1L;

            /**
            * 自增列
            */
            @TableId(value = "province_id", type = IdType.AUTO)
    private Integer provinceId;

            /**
            * 省份代码
            */
    private String provinceCode;

            /**
            * 省份名称
            */
    private String provinceName;

            /**
            * 简称
            */
    private String shortName;

            /**
            * 经度
            */
    private String lng;

            /**
            * 纬度
            */
    private String lat;

            /**
            * 排序
            */
    private Integer sort;

            /**
            * 创建时间
            */
    private Date createTime;

            /**
            * 修改时间
            */
    private Date updateTime;

            /**
            * 备注
            */
    private String remark;

            /**
            * 状态
            */
    private Integer state;


    @Override
    protected Serializable pkVal() {
        return this.provinceId;
    }

}
