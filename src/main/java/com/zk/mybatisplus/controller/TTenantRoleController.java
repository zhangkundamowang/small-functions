package com.zk.mybatisplus.controller;

import com.zk.mybatisplus.model.TTenantRole;
import com.zk.mybatisplus.model.TTenantUser;
import com.zk.mybatisplus.service.TTenantRoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色管理
 *
 * @author zk
 * @since 2021-09-06
 */
@RestController
@RequestMapping("/role")
public class TTenantRoleController {
    @Resource
    private TTenantRoleService service;


    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    public List<TTenantRole> findAll() {
        return service.findAll();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public TTenantRole findById(@RequestParam(value = "id") int id) {
        return service.findById(id);
    }

    /**
     * 查询该角色下的所有用户 一个角色对应多个用户
     */
    @RequestMapping(value = "/findUsersByRoleId", method = RequestMethod.POST)
    public List<TTenantUser> findUsersByRoleId(@Param("id") Integer id) {
        return service.findUsersByRoleId(id);
    }
}
