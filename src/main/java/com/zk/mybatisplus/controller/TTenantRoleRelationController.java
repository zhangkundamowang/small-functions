package com.zk.mybatisplus.controller;

import com.zk.mybatisplus.mapper.TTenantRoleRelationMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zk
 * @since 2021-09-08
 */
@RestController
@RequestMapping("/relation")
@Api(tags = "用户角色关系管理",value = "用户角色关系管理")
public class TTenantRoleRelationController {

    @Resource
    private TTenantRoleRelationMapper mapper;

    @RequestMapping(value = "/getDistance", method = RequestMethod.POST)
    @ApiOperation(value = "测试两个经纬度之间的距离")
    public Double get() {
        return mapper.getDistance(106.486654,29.490295,106.581515,29.615467);
    }
}
