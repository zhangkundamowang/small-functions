package com.zk.mybatisplus.controller;

import com.zk.mybatisplus.mapper.TTenantRoleRelationMapper;
import com.zk.mybatisplus.service.TTenantRoleRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @author zk
 * @since 2021-09-08
 */
@RestController
@RequestMapping("/relation")
@Api(tags = "用户角色关系管理", value = "用户角色关系管理")
public class BaiDuMapController {

    @Resource
    private TTenantRoleRelationMapper mapper;

    @Resource
    private TTenantRoleRelationService service;

    @RequestMapping(value = "/getDistance", method = RequestMethod.POST)
    @ApiOperation(value = "测试两个经纬度之间的距离")
    public Double get() {
        return mapper.getDistance(106.486654, 29.490295, 106.581515, 29.615467);
    }

    /**
     * 参数格式 纬度，经度
     * @param origin    33.961656,116.804537
     * @param destination 30.539222,117.121283
     */
    @RequestMapping(value = "/getDirection", method = RequestMethod.GET)
    @ApiOperation(value = "两个地点距离和驾车耗时")
    public  Map<Object,Object> getDirection(@ApiParam(name = "origin", value = "起源")
                            @RequestParam(value = "origin", required = false) String origin,
                            @ApiParam(name = "destination", value = "目的地")
                            @RequestParam(value = "destination", required = false) String destination) {
        return service.getDirection(origin,destination);
    }


}
