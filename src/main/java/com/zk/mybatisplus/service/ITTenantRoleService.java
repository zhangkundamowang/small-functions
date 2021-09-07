package com.zk.mybatisplus.service;

import com.zk.mybatisplus.model.TTenantRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-09-06
 */
public interface ITTenantRoleService extends IService<TTenantRole> {
    List<TTenantRole> findAll();
    TTenantRole findById(Integer id);
}
