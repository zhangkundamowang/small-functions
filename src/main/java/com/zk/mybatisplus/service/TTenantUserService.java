package com.zk.mybatisplus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zk.mybatisplus.model.TTenantRole;
import com.zk.mybatisplus.model.TTenantUser;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @since 2021-09-08
 */
public interface TTenantUserService extends IService<TTenantUser> {

    Page<TTenantUser> findUserByPage(Integer pageNo, Integer pageSize);

    TTenantUser findUserById(Integer id);

    TTenantUser findUserByName(String name);

    TTenantRole findRoleUnderUser(Integer roleId);

    /**
     * 登录
     */
    Map<String, Object> login(HttpServletRequest request, String userName, String password);

    /**
     * 注册
     */
    boolean auth(String userName, String pwd);

    /**
     * 查找半个月内登陆的用户
     */
    List<TTenantUser> findByTime();
}
