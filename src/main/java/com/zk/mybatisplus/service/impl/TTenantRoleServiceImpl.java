package com.zk.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zk.mybatisplus.config.mybatisplus.PageFactory;
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
    public IPage<TTenantRole> findAll() {
        LambdaQueryWrapper<TTenantRole> query= Wrappers.lambdaQuery();
        query.eq(TTenantRole::getValid,"Y");

        Page<TTenantRole> page=PageFactory.page();

        IPage<TTenantRole> tTenantRoleIPage = mapper.selectPage(page, query);
        return  tTenantRoleIPage;
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
