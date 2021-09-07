package com.zk.mybatisplus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zk.mybatisplus.model.TTenantUser;
import com.zk.mybatisplus.service.ITTenantUserService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户管理
 * @author zk
 * @since 2021-09-06
 */
@RestController
@RequestMapping("/user")
public class TTenantUserController {

    @Resource
    private ITTenantUserService service;

    @RequestMapping(value = "/findAll",method = RequestMethod.POST)
    public Object findAll(HttpServletRequest request){
        //获取前台发送过来的数据
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        Integer pageSize = Integer.valueOf(request.getParameter("pageSize"));
        IPage<TTenantUser> page = new Page<>(pageNo, pageSize);
        QueryWrapper<TTenantUser> wrapper = new QueryWrapper<>();
        TTenantUser user = new TTenantUser();
        user.setAddress("上海");
        wrapper.setEntity(user);
        return service.page(page,wrapper);
    }

}
