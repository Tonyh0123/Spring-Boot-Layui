package com.tangtang.manager.dao;


import com.tangtang.manager.dto.AdminUserDTO;
import com.tangtang.manager.pojo.BaseAdminUser;
import com.tangtang.manager.dto.UserSearchDTO;
import tk.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseAdminUserMapper extends MyMapper<BaseAdminUser> {

    List<AdminUserDTO> getUserList(UserSearchDTO userSearchDTO);

    BaseAdminUser getUserByUserName(@Param("sysUserName")String sysUserName,@Param("id") Integer id);

    BaseAdminUser getUserByPhone(@Param("userPhone")String userPhone, @Param("id") Integer id);

    int updateUserStatus(@Param("id") Integer id,@Param("status") Integer status);

    int updateUser(BaseAdminUser user);

    BaseAdminUser findByUserName(@Param("userName") String userName);

    int updatePwd(@Param("userName") String userName,@Param("password") String password);

    Integer checkStudentEmail(@Param("Email") String Email);

    Integer checkSchoolEmail(@Param("Email") String Email);

    Integer checkCompanyEmail(@Param("Email") String Email);

    Integer checkSchoolPhone(@Param("phone") String phone);

    Integer checkCompanyPhone(@Param("phone") String phone);



}