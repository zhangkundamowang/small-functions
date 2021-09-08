package com.zk.mybatisplus.service;

import com.zk.mybatisplus.model.TTenantRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zk
 * @since 2021-09-08
 */
public interface TTenantRoleService extends IService<TTenantRole> {

    List<TTenantRole> findAll();

    TTenantRole findById(Integer id);
}
