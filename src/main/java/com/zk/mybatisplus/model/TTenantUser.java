package com.zk.mybatisplus.model;

    import com.baomidou.mybatisplus.annotation.IdType;
    import com.baomidou.mybatisplus.extension.activerecord.Model;
    import com.baomidou.mybatisplus.annotation.TableId;
    import java.time.LocalDateTime;
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;

/**
* <p>
    * 
    * </p>
*
* @author astupidcoder
* @since 2021-09-06
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public class TTenantUser extends Model {

    private static final long serialVersionUID = 1L;

            /**
            * 主键id
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 机构编号
            */
    private String tenantAccounts;

            /**
            * 用户名
            */
    private String userName;

            /**
            * 用户手机号
            */
    private String mobile;

            /**
            * 登录账号
            */
    private String accounts;

            /**
            * 登录密码
            */
    private String password;

            /**
            * 角色id（一对一）
            */
    private Integer roleId;

            /**
            * 部门id（一对一）
            */
    private Integer departmentId;

            /**
            * 用户状态 Y:可用  N:不可用
            */
    private String valid;

            /**
            * 提示初始化密码（Y：已初始化  N：未要初始化）
            */
    private String initPassword;

            /**
            * 国家
            */
    private String country;

            /**
            * 省
            */
    private String province;

            /**
            * 城市
            */
    private String city;

            /**
            * 区（县）
            */
    private String district;

            /**
            * 地址
            */
    private String address;

            /**
            * 创建时间
            */
    private LocalDateTime createTime;

            /**
            * 更新时间
            */
    private LocalDateTime updateTime;


}
