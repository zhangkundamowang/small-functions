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
    public class TTenantRole extends Model<TTenantRole> {

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
            * 角色名称
            */
    private String roleName;

            /**
            * 角色编码
            */
    private String roleCode;

            /**
            * Y:可用  N:不可用
            */
    private String valid;

            /**
            * 创建时间
            */
    private Date createTime;

            /**
            * 更新时间
            */
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
