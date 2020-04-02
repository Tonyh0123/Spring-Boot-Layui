package com.tangtang.manager.pojo;

import javax.persistence.*;


@Table(name = "base_student")
public class BaseStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "sys_user_name")
    private String userName;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_student_name")
    private String studentName;

    @Column(name = "school_student_id")
    private String studentId;

    @Column(name = "school_student_email")
    private String studentEmail;

    @Column(name = "school_student_confirm_url")
    private String studentConfirm;

    @Column(name = "school_student_SFZ_ZM")
    private String SFZZM;

    @Column(name = "school_student_SFZ_BM")
    private String SFZBM;

    @Column(name = "school_student_status")
    private String studentStatus;

    @Column(name = "school_student_major")
    private String studentMajor;

    public Integer getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentConfirm() {
        return studentConfirm;
    }

    public void setStudentConfirm(String studentConfirm) {
        this.studentConfirm = studentConfirm;
    }

    public String getSFZZM() {
        return SFZZM;
    }

    public void setSFZZM(String SFZZM) {
        this.SFZZM = SFZZM;
    }

    public String getSFZBM() {
        return SFZBM;
    }

    public void setSFZBM(String SFZBM) {
        this.SFZBM = SFZBM;
    }

    public String getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(String studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }
}
