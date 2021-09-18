package com.zk.mybatisplus.mapper;

import com.zk.mybatisplus.model.TTenantRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zk.mybatisplus.model.TTenantUser;
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
public interface TTenantRoleMapper extends BaseMapper<TTenantRole> {

    List<TTenantRole> findAll();

    TTenantRole findById(@Param("id") Integer id);

    List<TTenantUser> findUsersByRoleId(@Param("id") Integer id);
}
