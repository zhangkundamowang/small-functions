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
    public class TTenantRoleRelation extends Model {

    private static final long serialVersionUID = 1L;

            /**
            * 主键id
            */
            @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

            /**
            * 角色id
            */
    private Integer roleId;

            /**
            * 菜单操作关系id
            */
    private Integer relationId;

            /**
            * 1:角色和菜单操作关联(t_tenant_menu_operation_relation)  2:角色和父菜单关联(t_tenant_menu)
            */
    private Integer type;

            /**
            * 创建时间
            */
    private LocalDateTime createTime;

            /**
            * 更新时间
            */
    private LocalDateTime updateTime;


}
