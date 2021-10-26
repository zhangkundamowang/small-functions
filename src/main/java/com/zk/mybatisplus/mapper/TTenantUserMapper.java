package com.zk.mybatisplus.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zk.mybatisplus.model.TTenantRole;
import com.zk.mybatisplus.model.TTenantUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zk
 * @since 2021-09-08
 */
public interface TTenantUserMapper extends BaseMapper<TTenantUser> {


    @Select("SELECT * FROM t_tenant_user WHERE id < #{map.id}")
    List<TTenantUser> selectUserPage(@Param("map") Map<String, Object> map, Page<TTenantUser> page);

    TTenantUser getUserById(@Param("id") Integer id);

    TTenantUser getUserByName(@Param("name") String name);

    TTenantUser selectUserByName(@Param("name") String name);

    TTenantUser selectUserByNameAndPwd(@Param("name") String name,@Param("password") String password);

    /**
     * 注意@Param("roleId") 这是传的参数
     */
    TTenantRole findRoleUnderUser(@Param("roleId") Integer roleId);

}
