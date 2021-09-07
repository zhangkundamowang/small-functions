package com.zk.mybatisplus.service.impl;

import com.zk.mybatisplus.model.TTenantRole;
import com.zk.mybatisplus.mapper.TTenantRoleMapper;
import com.zk.mybatisplus.service.ITTenantRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author astupidcoder
 * @since 2021-09-06
 */
@Service
public class TTenantRoleServiceImpl extends ServiceImpl<TTenantRoleMapper, TTenantRole> implements ITTenantRoleService {
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
}
