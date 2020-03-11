package com.tangtang.manager.dto;

;import lombok.Data;

/**
 * @Title: AdminUserDTO
 * @Description:
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/03/3 12:13
 */
@Data
public class AdminUserDTO {

    private Integer id;

    private String sysUserName;

    private String sysUserPwd;

    private Integer roleId;

    private String roleName;

    private String userPhone;


    private String regTime;


    private Integer userStatus;

}
