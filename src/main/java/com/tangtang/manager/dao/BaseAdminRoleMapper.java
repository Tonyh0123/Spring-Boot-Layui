package com.tangtang.manager.dao;

import com.tangtang.manager.dto.UserSearchDTO;
import com.tangtang.manager.pojo.BaseAdminRole;
import tk.mapper.MyMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseAdminRoleMapper extends MyMapper<BaseAdminRole> {

    List<BaseAdminRole> getRoleList(Integer pageNum, Integer pageSize);

    List<BaseAdminRole> getRoles();

    int updateRole(BaseAdminRole role);

    int updateRoleStatus(@Param("id") Integer id,@Param("roleStatus") Integer roleStatus);

}