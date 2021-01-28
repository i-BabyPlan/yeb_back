package com.zoux.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zoux.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zoux
 * @since 2021-01-23
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据用户id查询角色列表
     * @return
     */
    List<Role> getRoles(Integer adminId);
}
