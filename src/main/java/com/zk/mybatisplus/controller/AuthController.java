package com.zk.mybatisplus.controller;

import com.zk.mybatisplus.service.TTenantUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * jwt  session 结合过滤器实现登陆登出
 */

@RestController
@RequestMapping("/root")
@Api(value = "登录注册管理", tags = "登录注册管理")//tags起作用
public class AuthController {

    @Resource
    private TTenantUserService userService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ApiOperation(value = "注册")
    public Boolean auth(@ApiParam(name = "userName", value = "用户名")
                        @RequestParam("userName") String userName,
                        @ApiParam(name = "pwd", value = "密码")
                        @RequestParam("pwd") String pwd) {
        return userService.auth(userName, pwd);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录")
    public Map<String, Object> login(HttpServletRequest request,
                                     @ApiParam(name = "userName", value = "用户名")
                                     @RequestParam("userName") String userName,
                                     @ApiParam(name = "password", value = "密码")
                                     @RequestParam("password") String password) {
        return userService.login(request, userName, password);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(value = "登出")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }


}
