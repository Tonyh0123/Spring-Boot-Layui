package com.tangtang.manager.pojo;

import javax.persistence.*;

@Table(name = "base_policy")
public class BasePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "policy_title")
    private String policyTitle;

    @Column(name = "policy_date")
    private String policyDate;

    @Column(name = "policy_url")
    private String policyUrl;

    @Column(name = "policy_located")
    private String policyLocated;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPolicyTitle() {
        return policyTitle;
    }

    public void setPolicyTitle(String policyTitle) {
        this.policyTitle = policyTitle;
    }

    public String getPolicyDate() {
        return policyDate;
    }

    public void setPolicyDate(String policyDate) {
        this.policyDate = policyDate;
    }

    public String getPolicyUrl() {
        return policyUrl;
    }

    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl;
    }

    public String getPolicyLocated() {
        return policyLocated;
    }

    public void setPolicyLocated(String policyLocated) {
        this.policyLocated = policyLocated;
    }
}
