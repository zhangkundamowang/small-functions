package com.zk.mybatisplus.service.impl;

import com.zk.mybatisplus.model.TTenantRole;
import com.zk.mybatisplus.model.TTenantUser;
import com.zk.mybatisplus.mapper.TTenantUserMapper;
import com.zk.mybatisplus.service.TTenantUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zk
 * @since 2021-09-08
 */
@Service
public class TTenantUserServiceImpl extends ServiceImpl<TTenantUserMapper, TTenantUser> implements TTenantUserService {

    @Resource
    private TTenantUserMapper userMapper;

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


}
