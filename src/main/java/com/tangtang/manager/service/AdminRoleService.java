package com.tangtang.manager.service;

import com.tangtang.manager.pojo.BaseAdminRole;
import com.tangtang.manager.response.PageDataResult;

import java.util.List;
import java.util.Map;

/**
 * @Title: AdminRoleService
 * @Description:
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/03/09 14:25
 */
public interface AdminRoleService {

    PageDataResult getRoleList(Integer pageNum, Integer pageSize);

    List<BaseAdminRole> getRoles();

    BaseAdminRole findRoleById(Integer id);

    Map<String,Object> updateRole(BaseAdminRole role);

    Map<String,Object> delRole(Integer id,Integer status);

    Map<String,Object> recoverRole(Integer id,Integer status);

    Map<String,Object> addRole(BaseAdminRole role);

}
