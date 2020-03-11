package com.tangtang.manager.service;

import com.tangtang.manager.dto.SchoolRegistrationDTO;
import com.tangtang.manager.dto.StudentRegistrationDTO;
import com.tangtang.manager.pojo.BaseAdminUser;
import com.tangtang.manager.dto.UserSearchDTO;
import com.tangtang.manager.response.PageDataResult;

import java.util.Map;


/**
 * @Title: AdminUserService
 * @Description:
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/03/09 11:04
 */
public interface AdminUserService {

    PageDataResult getUserList(UserSearchDTO userSearch, Integer pageNum, Integer pageSize);

    Map<String,Object> addUser(BaseAdminUser user);

    Map<String,Object> regUser(StudentRegistrationDTO studentRegistrationDTO);

    Map<String,Object> regSchoolUser(SchoolRegistrationDTO schoolRegistrationDTO);

    Map<String,Object> updateUser(BaseAdminUser user);

    BaseAdminUser getUserById(Integer id);

    BaseAdminUser findByUserName(String userName);

    int updatePwd(String userName,String password);

    Map<String, Object> delUser(Integer id,Integer status);

    Map<String, Object> recoverUser(Integer id,Integer status);
}
