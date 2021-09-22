package com.zk.mybatisplus.service;

import com.zk.mybatisplus.model.TTenantRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zk.mybatisplus.model.TTenantUser;
import java.util.List;

/**
 * @author zk
 * @since 2021-09-08
 */
public interface TTenantRoleService extends IService<TTenantRole> {

    List<TTenantRole> findAll();

    TTenantRole findById(Integer id);

    List<TTenantUser> findUsersByRoleId(Integer id);

}
