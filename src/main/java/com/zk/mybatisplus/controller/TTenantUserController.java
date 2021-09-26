package com.zk.mybatisplus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zk.mybatisplus.model.TTenantRole;
import com.zk.mybatisplus.model.TTenantUser;
import com.zk.mybatisplus.service.TTenantUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * 用户管理
 *
 * @author zk
 * @since 2021-09-06
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户管理！", tags = "用户管理")
public class TTenantUserController {

    @Resource
    private TTenantUserService userService;

    /**
     * 第二种方式，使用mapper文件的select注解，优点是可以方便的建立查询语句，可以联合多表查询。
     */
    @RequestMapping(value = "/findUserByPage", method = RequestMethod.POST)
    @ApiOperation(value = "分页获取所有用户")
    public Page<TTenantUser> findUserByPage(
            @ApiParam(name = "pageNo", value = "当前页")
            @RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
            @ApiParam(name = "pageSize", value = "每一页数据个数")
            @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

        return userService.findUserByPage(pageNo, pageSize);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @ApiOperation(value = "通过id查找用户")
    public TTenantUser findUserById(
            @ApiParam(name = "id", value = "用户id")
            @RequestParam(value = "id", required = true) Integer id) {
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/findByName", method = RequestMethod.POST)
    @ApiOperation(value = "通过name查找用户")
    public TTenantUser findUserByName(
            @ApiParam(name = "name", value = "用户名")
            @RequestParam(value = "name", required = true) String name) {
        return userService.findUserByName(name);
    }


    /**
     * 查询该用户对应的角色  多个用户对应一个角色 多对一
     */
    @PostMapping(value = "/findRoleUnderUser")
    @ApiIgnore
    public TTenantRole findRolesUnderUser(@Param("roleId") Integer roleId) {
        return userService.findRoleUnderUser(roleId);
    }

}