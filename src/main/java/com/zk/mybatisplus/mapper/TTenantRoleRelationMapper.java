package com.zk.mybatisplus.mapper;

import com.zk.mybatisplus.model.TTenantRoleRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zk
 * @since 2021-09-08
 */
public interface TTenantRoleRelationMapper extends BaseMapper<TTenantRoleRelation> {
    /**
     * 测试两个经纬度之间的距离
     */
    Double getDistance(@Param("lng1")double lng1,
                                 @Param("lat1")double lat1,
                                 @Param("lng2")double lng2,
                                 @Param("lat2")double lat2);

}
