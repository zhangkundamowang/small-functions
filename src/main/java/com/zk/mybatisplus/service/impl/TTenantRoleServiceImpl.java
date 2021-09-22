package com.zk.mybatisplus.service.impl;

import com.zk.mybatisplus.model.TTenantRole;
import com.zk.mybatisplus.mapper.TTenantRoleMapper;
import com.zk.mybatisplus.model.TTenantUser;
import com.zk.mybatisplus.service.TTenantRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author zk
 * @since 2021-09-08
 */
@Service
public class TTenantRoleServiceImpl extends ServiceImpl<TTenantRoleMapper, TTenantRole> implements TTenantRoleService {

    @Resource
    private TTenantRoleMapper mapper;

    @Override
    public List<TTenantRole> findAll() {
        return mapper.findAll();
    }

    @Override
    public TTenantRole findById(Integer id) {
        return mapper.findById(id);
    }

    @Override
    public List<TTenantUser> findUsersByRoleId(Integer id) {
        return mapper.findUsersByRoleId(id);
    }
}
