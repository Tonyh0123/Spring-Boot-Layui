package com.tangtang.manager.pojo;

import javax.persistence.*;

@Table(name = "base_investor_finance")
public class BaseInvestorFinance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "investor_id")
    private String investor_id;

    @Column(name = "platform_user_name")
    private String platform_user_name;

    @Column(name = "investor_name")
    private String investor_name;

    @Column(name = "belong_company")
    private String belong_company;

    @Column(name = "investor_position")
    private String investor_position;

    @Column(name = "investor_work_years")
    private String investor_work_years;

    @Column(name = "belong_province")
    private String belong_province;

    @Column(name = "main_turn")
    private String main_turn;

    @Column(name = "single_investment")
    private String single_investment;

    @Column(name = "investment_field")
    private String investment_field;

    @Column(name = "personal_introduction")
    private String personal_introduction;

    @Column(name = "organization_name")
    private String organization_name;

    @Column(name = "organization_location")
    private String organization_location;

    @Column(name = "organization_ZJGM")
    private String organization_ZJGM;

    @Column(name = "organization_introduction")
    private String organization_introduction;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvestor_id() {
        return investor_id;
    }

    public void setInvestor_id(String investor_id) {
        this.investor_id = investor_id;
    }

    public String getPlatform_user_name() {
        return platform_user_name;
    }

    public void setPlatform_user_name(String platform_user_name) {
        this.platform_user_name = platform_user_name;
    }

    public String getInvestor_name() {
        return investor_name;
    }

    public void setInvestor_name(String investor_name) {
        this.investor_name = investor_name;
    }

    public String getBelong_company() {
        return belong_company;
    }

    public void setBelong_company(String belong_company) {
        this.belong_company = belong_company;
    }

    public String getInvestor_position() {
        return investor_position;
    }

    public void setInvestor_position(String investor_position) {
        this.investor_position = investor_position;
    }

    public String getInvestor_work_years() {
        return investor_work_years;
    }

    public void setInvestor_work_years(String investor_work_years) {
        this.investor_work_years = investor_work_years;
    }

    public String getBelong_province() {
        return belong_province;
    }

    public void setBelong_province(String belong_province) {
        this.belong_province = belong_province;
    }

    public String getMain_turn() {
        return main_turn;
    }

    public void setMain_turn(String main_turn) {
        this.main_turn = main_turn;
    }

    public String getSingle_investment() {
        return single_investment;
    }

    public void setSingle_investment(String single_investment) {
        this.single_investment = single_investment;
    }

    public String getInvestment_field() {
        return investment_field;
    }

    public void setInvestment_field(String investment_field) {
        this.investment_field = investment_field;
    }

    public String getPersonal_introduction() {
        return personal_introduction;
    }

    public void setPersonal_introduction(String personal_introduction) {
        this.personal_introduction = personal_introduction;
    }

    public String getOrganization_name() {
        return organization_name;
    }

    public void setOrganization_name(String organization_name) {
        this.organization_name = organization_name;
    }

    public String getOrganization_location() {
        return organization_location;
    }

    public void setOrganization_location(String organization_location) {
        this.organization_location = organization_location;
    }

    public String getOrganization_ZJGM() {
        return organization_ZJGM;
    }

    public void setOrganization_ZJGM(String organization_ZJGM) {
        this.organization_ZJGM = organization_ZJGM;
    }

    public String getOrganization_introduction() {
        return organization_introduction;
    }

    public void setOrganization_introduction(String organization_introduction) {
        this.organization_introduction = organization_introduction;
    }
}
