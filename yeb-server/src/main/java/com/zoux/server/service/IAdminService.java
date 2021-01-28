package com.zoux.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zoux.server.pojo.Admin;
import com.zoux.server.pojo.Menu;
import com.zoux.server.pojo.RespBean;
import com.zoux.server.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zoux
 * @since 2021-01-23
 */
public interface IAdminService extends IService<Admin> {
    /**
     * 登陆之后返回token
     *
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    Admin getAdminByUserName(String username);

    /**
     * 根据用户id查询角色列表
     *
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);
}
