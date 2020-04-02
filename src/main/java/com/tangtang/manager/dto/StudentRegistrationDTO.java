package com.tangtang.manager.dto;

import lombok.Data;

@Data
public class StudentRegistrationDTO {
    private Integer id;

    private String sysUserName;

    private String sysUserPwd;

    private Integer roleId;

    private String roleName;

    private String userPhone;

    private String regTime;

    private Integer userStatus;

    private String schoolName;

    private String companyName;

    private String studentId;

    private String realName;

    private String studentEmail;

    private String identifyConfirmFile;  //学生证明文件（学生证）

    private String studentStatus;     //学生身份核验状态

    private String studentID_SFZZM;   //身份证正面

    private String studentID_SFZBM;   //身份证背面

    private String studentMajor;      //专业名称

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getStudentID_SFZZM() {
        return studentID_SFZZM;
    }

    public void setStudentID_SFZZM(String studentID_SFZZM) {
        this.studentID_SFZZM = studentID_SFZZM;
    }

    public String getStudentID_SFZBM() {
        return studentID_SFZBM;
    }

    public void setStudentID_SFZBM(String studentID_SFZBM) {
        this.studentID_SFZBM = studentID_SFZBM;
    }

    public String getIdentifyConfirmFile() {
        return identifyConfirmFile;
    }

    public void setIdentifyConfirmFile(String identifyConfirmFile) {
        this.identifyConfirmFile = identifyConfirmFile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getSysUserPwd() {
        return sysUserPwd;
    }

    public void setSysUserPwd(String sysUserPwd) {
        this.sysUserPwd = sysUserPwd;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }
}
