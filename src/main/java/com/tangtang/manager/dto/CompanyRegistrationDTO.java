package com.tangtang.manager.dto;

public class CompanyRegistrationDTO {

    private Integer id; //base_admin_user表中的主键id

    private Integer company_id; //base_company表中的主键id

    //----------------------------------
    private String sysUserName;

    private String sysUserPwd;

    private Integer roleId;

    private String roleName;  //此处为base_admin_user表中字段

    private String regTime;

    private Integer userStatus;

    private String company_contacts_phone; //此字段对应base_admin_user表中的userPhone
    //--------------------------------------

    private String apply_status;
                                        //此处为base_company表中字段
    private String apply_opinion;

    private String company_contacts_email;
    //--------------------------------------

    private String applyFlag; //通过审核与否标志

    public String getApplyFlag() {
        return applyFlag;
    }

    public void setApplyFlag(String applyFlag) {
        this.applyFlag = applyFlag;
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

    public String getCompany_contacts_phone() {
        return company_contacts_phone;
    }

    public void setCompany_contacts_phone(String company_contacts_phone) {
        this.company_contacts_phone = company_contacts_phone;
    }

    public String getApply_status() {
        return apply_status;
    }

    public void setApply_status(String apply_status) {
        this.apply_status = apply_status;
    }

    public String getApply_opinion() {
        return apply_opinion;
    }

    public void setApply_opinion(String apply_opinion) {
        this.apply_opinion = apply_opinion;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public String getCompany_contacts_email() {
        return company_contacts_email;
    }

    public void setCompany_contacts_email(String company_contacts_email) {
        this.company_contacts_email = company_contacts_email;
    }
}
