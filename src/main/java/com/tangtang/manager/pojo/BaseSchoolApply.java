package com.tangtang.manager.pojo;

import javax.persistence.*;

@Table(name = "base_school")
public class BaseSchoolApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer keyid;

    @Column(name = "id")
    private Integer id;

    @Column(name = "sys_user_name")
    private String sysUserName;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_manager_name")
    private String schoolManagerName;

    @Column(name = "school_manager_job")
    private String schoolManagerJob;

    @Column(name = "school_manager_phone")
    private String schoolManagerPhone;

    @Column(name = "school_manager_email")
    private String schoolManagerEmail;

    @Column(name = "school_job_confirm")
    private String jobConfirm;

    @Column(name = "school_confirm")
    private String schoolConfirm;

    @Column(name = "apply_status")
    private Integer applyStatus;

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

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Integer getKeyid() {
        return keyid;
    }

    public void setKeyid(Integer keyid) {
        this.keyid = keyid;
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

    public String getSchoolManagerJob() {
        return schoolManagerJob;
    }

    public void setSchoolManagerJob(String schoolManagerJob) {
        this.schoolManagerJob = schoolManagerJob;
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
}
