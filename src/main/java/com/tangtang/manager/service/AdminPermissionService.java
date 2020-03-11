package com.tangtang.manager.service;

import com.tangtang.manager.dto.PermissionDTO;
import com.tangtang.manager.pojo.BaseAdminUser;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.pojo.BaseAdminPermission;

import java.util.List;
import java.util.Map;

/**
 * @Title: PermissionService
 * @Description:
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/03/07 9:44
 */
public interface AdminPermissionService {

    /**
     *
     * 功能描述: 添加权限
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/07 9:48
     */
    Map<String,Object> addPermission(BaseAdminPermission permission);

    /**
     *
     * 功能描述: 修改权限
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/07 9:48
     */
    Map<String,Object> updatePermission(BaseAdminPermission permission);

    /**
     *
     * 功能描述: 获取权限菜单列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/07 11:35
     */
    PageDataResult getPermissionList(Integer pageNum, Integer pageSize);

    /**
     *
     * 功能描述: 获取根权限菜单列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/07 11:36
     */
    List<PermissionDTO> parentPermissionList();

    /**
     *
     * 功能描述: 删除权限菜单
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/07 12:04
     */
    Map<String, Object> del(long id);

    /**
     *
     * 功能描述: 根据id获取权限
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/4 13:51
     */
    BaseAdminPermission getById(Object id);


    /**
     *
     * 功能描述: 获取当前登陆用户的权限
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/4 13:51
     */
    Map<String, Object> getUserPerms(BaseAdminUser user);

}
