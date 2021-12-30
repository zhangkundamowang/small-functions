package com.zk.mybatisplus.service;

import com.zk.mybatisplus.model.TTenantRoleRelation;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;

public interface TTenantRoleRelationService extends IService<TTenantRoleRelation> {

    Map<Object, Object> getDirection(String origin, String destination);

}
