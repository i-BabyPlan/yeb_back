package com.zoux.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zoux.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zoux
 * @since 2021-01-23
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 通过用户Id查询菜单列表
     *
     * @return
     */
    List<Menu> getMenusByAdminId();

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenusWithRole();
}
