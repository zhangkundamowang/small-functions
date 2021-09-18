package com.zk.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zk.mybatisplus.model.TTenantRole;
import com.zk.mybatisplus.model.TTenantUser;
import com.zk.mybatisplus.service.TTenantUserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户管理
 *
 * @author zk
 * @since 2021-09-06
 */
@RestController
@RequestMapping("/user")
public class TTenantUserController {

    @Resource
    private TTenantUserService userService;

    /**
     * 分页
     */
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public IPage<TTenantUser> findAll(HttpServletRequest request) {
        //获取前台发送过来的数据
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        IPage<TTenantUser> page = new Page<>(pageNo, pageSize);
        QueryWrapper<TTenantUser> wrapper = new QueryWrapper<>();
        TTenantUser user = new TTenantUser();
        // user.setAddress("上海");
        wrapper.setEntity(user);
        return userService.page(page, wrapper);
    }


    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public TTenantUser findUserById(@Param("id") Integer id) {
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.POST)
    public TTenantUser findUserByName(@Param("name") String name) {
        return userService.findUserByName(name);
    }

    /**
     * 查询该用户对应的角色  多个用户对应一个角色
     */
    @RequestMapping(value = "/findRoleUnderUser", method = RequestMethod.POST)
    public TTenantRole findRolesUnderUser(@Param("roleId") Integer roleId) {
        return userService.findRoleUnderUser(roleId);
    }

}