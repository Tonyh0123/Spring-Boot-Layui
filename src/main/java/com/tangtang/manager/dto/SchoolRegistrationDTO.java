package com.tangtang.manager.dto;

public class SchoolRegistrationDTO {
    private Integer keyid;

    private Integer id;

    private String sysUserName;

    private String sysUserPwd;

    private Integer roleId;

    private String roleName;

    private String userPhone;

    private String regTime;

    private Integer userStatus;

    private String schoolName;

    private String schoolManagerName;

    private String schoolManagerJob;

    private String schoolManagerPhone;

    private String schoolManagerEmail;

    private Integer applyStatus;

    private String jobConfirm;

    private String schoolConfirm;

    public String getJobConfirm() {
        return jobConfirm;
    }

    public void setJobConfirm(String jobConfirm) {
        this.jobConfirm = jobConfirm;
    }

    public String getSchoolConfirm() {
        return schoolConfirm;
    }

    public void setSchoolConfirm(String schoolConfirm) {
        this.schoolConfirm = schoolConfirm;
    }

    public Integer getKeyid() {
        return keyid;
    }

    public void setKeyid(Integer keyid) {
        this.keyid = keyid;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
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

    public String getSchoolManagerName() {
        return schoolManagerName;
    }

    public void setSchoolManagerName(String schoolManagerName) {
        this.schoolManagerName = schoolManagerName;
    }

    public String getSchoolManagerPhone() {
        return schoolManagerPhone;
    }

    public void setSchoolManagerPhone(String schoolManagerPhone) {
        this.schoolManagerPhone = schoolManagerPhone;
    }

    public String getSchoolManagerEmail() {
        return schoolManagerEmail;
    }

    public void setSchoolManagerEmail(String schoolManagerEmail) {
        this.schoolManagerEmail = schoolManagerEmail;
    }

    public String getSchoolManagerJob() {
        return schoolManagerJob;
    }

    public void setSchoolManagerJob(String schoolManagerJob) {
        this.schoolManagerJob = schoolManagerJob;
    }
}
