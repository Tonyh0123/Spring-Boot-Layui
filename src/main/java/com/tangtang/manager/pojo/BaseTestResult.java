package com.tangtang.manager.pojo;

import javax.persistence.*;

@Table(name = "base_test_result")
public class BaseTestResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "test_user_id")
    private String userId;

    @Column(name = "test_user_name")
    private String userName;

    @Column(name = "test_result")
    private String testResult;

    @Column(name = "ZYSZ")
    private double ZYSZ = 0.0;

    @Column(name = "CYSZ")
    private double CYSZ = 0.0;

    @Column(name = "GSSFZS")
    private double GSSFZS = 0.0;

    @Column(name = "CWYXZS")
    private double CWYXZS = 0.0;

    @Column(name = "YYGLZS")
    private double YYGLZS = 0.0;

    @Column(name = "CYJBNL")
    private double CYJBNL = 0.0;

    @Column(name = "YYGLNL")
    private double YYGLNL = 0.0;

    @Column(name = "SCYXNL")
    private double SCYXNL = 0.0;


    public double getZYSZ() {
        return ZYSZ;
    }

    public void setZYSZ(double ZYSZ) {
        this.ZYSZ = ZYSZ;
    }

    public double getCYSZ() {
        return CYSZ;
    }

    public void setCYSZ(double CYSZ) {
        this.CYSZ = CYSZ;
    }

    public double getGSSFZS() {
        return GSSFZS;
    }

    public void setGSSFZS(double GSSFZS) {
        this.GSSFZS = GSSFZS;
    }

    public double getCWYXZS() {
        return CWYXZS;
    }

    public void setCWYXZS(double CWYXZS) {
        this.CWYXZS = CWYXZS;
    }

    public double getYYGLZS() {
        return YYGLZS;
    }

    public void setYYGLZS(double YYGLZS) {
        this.YYGLZS = YYGLZS;
    }

    public double getCYJBNL() {
        return CYJBNL;
    }

    public void setCYJBNL(double CYJBNL) {
        this.CYJBNL = CYJBNL;
    }

    public double getYYGLNL() {
        return YYGLNL;
    }

    public void setYYGLNL(double YYGLNL) {
        this.YYGLNL = YYGLNL;
    }

    public double getSCYXNL() {
        return SCYXNL;
    }

    public void setSCYXNL(double SCYXNL) {
        this.SCYXNL = SCYXNL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }
}
