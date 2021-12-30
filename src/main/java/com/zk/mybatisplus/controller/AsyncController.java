package com.zk.mybatisplus.controller;

import com.zk.mybatisplus.service.AsyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value = "异步测试",tags = "异步测试")
@RestController
public class AsyncController {

    @Autowired
    private AsyncService asyncService;

    /**
     * 使用传统方式测试
     */
    @GetMapping("/test")
    @ApiOperation(value = "测试异步处理")
    public void test() {
        System.out.println("获取主线程名称：" + Thread.currentThread().getName());
        asyncService.execute();
        System.out.println("执行成功，返回结果");
    }
}
