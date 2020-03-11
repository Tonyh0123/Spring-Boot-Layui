package com.tangtang.manager.pojo;

import javax.persistence.*;

@Table(name = "base_successful_case")
public class BaseSuccessfulCase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "case_identifier")
    private String caseIdentifier;

    @Column(name = "case_title")
    private String caseTitle;

    @Column(name = "case_pic_url")
    private String casePicUrl;

    @Column(name = "case_role_name")
    private String caseRoleName;

    @Column(name = "case_gra_school_major")
    private String caseGraSchoolMajor;

    @Column(name = "case_entrepreneurial_journey")
    private String caseEntrepreneurialJourney;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaseIdentifier() {
        return caseIdentifier;
    }

    public void setCaseIdentifier(String caseIdentifier) {
        this.caseIdentifier = caseIdentifier;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getCasePicUrl() {
        return casePicUrl;
    }

    public void setCasePicUrl(String casePicUrl) {
        this.casePicUrl = casePicUrl;
    }

    public String getCaseRoleName() {
        return caseRoleName;
    }

    public void setCaseRoleName(String caseRoleName) {
        this.caseRoleName = caseRoleName;
    }

    public String getCaseGraSchoolMajor() {
        return caseGraSchoolMajor;
    }

    public void setCaseGraSchoolMajor(String caseGraSchoolMajor) {
        this.caseGraSchoolMajor = caseGraSchoolMajor;
    }

    public String getCaseEntrepreneurialJourney() {
        return caseEntrepreneurialJourney;
    }

    public void setCaseEntrepreneurialJourney(String caseEntrepreneurialJourney) {
        this.caseEntrepreneurialJourney = caseEntrepreneurialJourney;
    }
}
