package com.tangtang.manager.pojo;

import javax.persistence.*;

@Table(name = "base_finance")
public class BaseFinance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_id")
    private String project_id;

    @Column(name = "project_name")
    private String project_name;

    @Column(name = "finance_company")
    private String finance_company;

    @Column(name = "finance_money")
    private String finance_money;

    @Column(name = "finance_type")
    private String finance_type;

    @Column(name = "finance_get_time")
    private String finance_get_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProject_id() {
        return project_id;
    }

    public void setProject_id(String project_id) {
        this.project_id = project_id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getFinance_company() {
        return finance_company;
    }

    public void setFinance_company(String finance_company) {
        this.finance_company = finance_company;
    }

    public String getFinance_money() {
        return finance_money;
    }

    public void setFinance_money(String finance_money) {
        this.finance_money = finance_money;
    }

    public String getFinance_type() {
        return finance_type;
    }

    public void setFinance_type(String finance_type) {
        this.finance_type = finance_type;
    }

    public String getFinance_get_time() {
        return finance_get_time;
    }

    public void setFinance_get_time(String finance_get_time) {
        this.finance_get_time = finance_get_time;
    }
}
