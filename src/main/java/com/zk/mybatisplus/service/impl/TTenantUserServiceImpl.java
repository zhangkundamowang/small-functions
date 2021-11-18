package com.zk.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zk.mybatisplus.common.utils.MD5Utils;
import com.zk.mybatisplus.common.utils.TokenUtil;
import com.zk.mybatisplus.model.TTenantRole;
import com.zk.mybatisplus.model.TTenantUser;
import com.zk.mybatisplus.mapper.TTenantUserMapper;
import com.zk.mybatisplus.service.TTenantUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zk
 * @since 2021-09-08
 */
@Service
public class TTenantUserServiceImpl extends ServiceImpl<TTenantUserMapper, TTenantUser> implements TTenantUserService {

    @Resource
    private TTenantUserMapper userMapper;

    @Override
    public Page<TTenantUser> findUserByPage(Integer pageNo, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", 100);
        Page<TTenantUser> page = new Page<>(pageNo, pageSize);
        page.setRecords(userMapper.selectUserPage(map, page));
        return page;
    }

    @Override
    public TTenantUser findUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public TTenantUser findUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    @Override
    public TTenantRole findRoleUnderUser(Integer roleId) {
        return userMapper.findRoleUnderUser(roleId);
    }

    @Override
    public Map<String,Object> login(HttpServletRequest request, String userName, String password) {
        Map<String,Object> map=new HashMap<>();
        try {
            TTenantUser user = userMapper.selectUserByName(userName);
            if (null != user) {
                request.getSession().setAttribute("userName", userName);
                String oldPwd = user.getPassword();
                if (MD5Utils.checkPwd(password, oldPwd)) {
                    request.getSession().setAttribute("password", MD5Utils.string2MD5(password));
                    String token = TokenUtil.sign(userName, LocalDateTime.now());
                    map.put("code",200);
                    map.put("message","登录成功");
                    map.put("token",token);
                } else {
                    throw new RuntimeException("密码错误");
                }
            } else {
                throw new RuntimeException("账号不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public boolean auth(String userName, String pwd) {
        boolean flag = false;
        try {
            TTenantUser user = new TTenantUser();
            user.setUserName(userName);
            user.setPassword(MD5Utils.string2MD5(pwd));
            System.out.println("注册时加密密码------" + user.getPassword());
            int row = userMapper.insert(user);
            if (row > 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public List<TTenantUser> findByTime() {
        //15天前时间戳
        long before=new Date().getTime()-(1000*60*60*24*15);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = format.format(new Date());
        String beforeTime = format.format(new Date(before));
        return userMapper.findByTime(nowTime,beforeTime);
    }


}
