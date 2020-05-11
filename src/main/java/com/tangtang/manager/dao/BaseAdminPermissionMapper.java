package com.tangtang.manager.dao;


import com.tangtang.manager.dto.PermissionDTO;
import com.tangtang.manager.dto.UserSearchDTO;
import com.tangtang.manager.pojo.BaseAdminPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface BaseAdminPermissionMapper extends MyMapper<BaseAdminPermission> {
    List<PermissionDTO> getPermissionList(Integer pageNum, Integer pageSize);

    List<PermissionDTO> parentPermissionList();

    int updatePermission(BaseAdminPermission permission);

    List<PermissionDTO> getPermissionListByPId(@Param("pid") Integer pid);
}