package com.zk.mybatisplus.mapper;

import com.zk.mybatisplus.model.TTenantRole;
import com.zk.mybatisplus.model.TTenantUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zk
 * @since 2021-09-08
 */
public interface TTenantUserMapper extends BaseMapper<TTenantUser> {

    TTenantUser getUserById(@Param("id") Integer id);

    TTenantUser getUserByName(@Param("name") String name);

    /**
     * 注意@Param("roleId") 这是传的参数
     */
   TTenantRole findRoleUnderUser(@Param("roleId") Integer roleId);

}
