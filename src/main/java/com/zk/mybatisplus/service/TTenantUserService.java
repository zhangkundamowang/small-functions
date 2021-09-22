package com.zk.mybatisplus.service;

import com.zk.mybatisplus.model.TTenantRole;
import com.zk.mybatisplus.model.TTenantUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author zk
 * @since 2021-09-08
 */
public interface TTenantUserService extends IService<TTenantUser> {

    TTenantUser findUserById(Integer id);

    TTenantUser findUserByName(String name);

    TTenantRole findRoleUnderUser(Integer roleId);

}
