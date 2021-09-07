package com.zk.mybatisplus.mapper;

import com.zk.mybatisplus.model.TTenantRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author astupidcoder
 * @since 2021-09-06
 */
@Mapper
public interface TTenantRoleMapper extends BaseMapper<TTenantRole> {

  List<TTenantRole> findAll();

  TTenantRole findById(@Param("id") Integer id);
}
