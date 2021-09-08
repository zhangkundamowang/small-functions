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
    * 
    * </p>
*
* @author zk
* @since 2021-09-08
*/
    @Data
        @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public class TTenantUser extends Model<TTenantUser> {

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
            * 机构名称
            */
    private String tenantName;

            /**
            * 部门id（一对一）
            */
    private Integer departmentId;

            /**
            * 员工号
            */
    private String employeeNo;

            /**
            * 登录账号
            */
    private String accounts;

            /**
            * 用户名
            */
    private String userName;

            /**
            * 用户手机号
            */
    private String mobile;

            /**
            * 登录密码
            */
    private String password;

            /**
            * 角色id（一对一）
            */
    private Integer roleId;

            /**
            * 用户状态 Y:可用  N:不可用
            */
    private String valid;

            /**
            * 省
            */
    private String province;

            /**
            * 城市
            */
    private String city;

            /**
            * 区
            */
    private String area;

            /**
            * 详细地址
            */
    private String address;

            /**
            * 密码
            */
    private String initPassword;

            /**
            * 最高权限 0：否  1：是
            */
    private Integer isRoot;

            /**
            * 创建时间
            */
    private Date createTime;

            /**
            * 更新时间
            */
    private Date updateTime;

            /**
            * 登录次数
            */
    private Integer loginCount;

            /**
            * 登录时间
            */
    private Date loginTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
