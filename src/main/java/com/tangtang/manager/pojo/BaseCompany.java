package com.tangtang.manager.pojo;

import javax.persistence.*;

@Table(name = "base_company")
public class BaseCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "company_name")
    private String company_name;

    @Column(name = "company_contacts")
    private String company_contacts;

    @Column(name = "company_contacts_phone")
    private String company_contacts_phone;

    @Column(name = "company_contacts_email")
    private String company_contacts_email;

    @Column(name = "company_industry")
    private String company_industry;

    @Column(name = "company_location")
    private String company_location;

    @Column(name = "company_staff_size")
    private String company_staff_size;

    @Column(name = "company_introduction")
    private String company_introduction;

    @Column(name = "company_business_license")
    private String company_business_license;

    @Column(name = "apply_status")
    private String apply_status;

    @Column(name = "apply_opinion")
    private String apply_opinion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_contacts() {
        return company_contacts;
    }

    public void setCompany_contacts(String company_contacts) {
        this.company_contacts = company_contacts;
    }

    public String getCompany_contacts_phone() {
        return company_contacts_phone;
    }

    public void setCompany_contacts_phone(String company_contacts_phone) {
        this.company_contacts_phone = company_contacts_phone;
    }

    public String getCompany_contacts_email() {
        return company_contacts_email;
    }

    public void setCompany_contacts_email(String company_contacts_email) {
        this.company_contacts_email = company_contacts_email;
    }

    public String getCompany_industry() {
        return company_industry;
    }

    public void setCompany_industry(String company_industry) {
        this.company_industry = company_industry;
    }

    public String getCompany_location() {
        return company_location;
    }

    public void setCompany_location(String company_location) {
        this.company_location = company_location;
    }

    public String getCompany_staff_size() {
        return company_staff_size;
    }

    public void setCompany_staff_size(String company_staff_size) {
        this.company_staff_size = company_staff_size;
    }

    public String getCompany_introduction() {
        return company_introduction;
    }

    public void setCompany_introduction(String company_introduction) {
        this.company_introduction = company_introduction;
    }

    public String getCompany_business_license() {
        return company_business_license;
    }

    public void setCompany_business_license(String company_business_license) {
        this.company_business_license = company_business_license;
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
}
