package com.tangtang.manager.pojo;

import javax.persistence.*;

@Table(name = "base_project_establish_report")
public class BaseProjectDeclaration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_owner_id")
    private String projectOwnerId;

    @Column(name = "project_owner_name")
    private String projectOwnerName;

    @Column(name = "project_id")
    private String projectId;

    @Column(name = "project_name")
    private String projectName;

    @Column(name = "project_key_words")
    private String projectKeyWords;

    @Column(name = "project_type")
    private String projectType;

    @Column(name = "project_belong_school")
    private String projectBelongSchool;

    @Column(name = "project_implementation_time")
    private String projectImplementationTime;

    @Column(name = "project_SSXKML")
    private String projectSSXKML;

    @Column(name = "project_SSZYDL")
    private String projectSSZYDL;

    @Column(name = "project_establish_time")
    private String projectEstablishTime;

    @Column(name = "project_members")
    private String projectMembers;

    @Column(name = "project_guidance_teacher")
    private String projectGuidanceTeacher;

    @Column(name = "project_introduction")
    private String projectIntroduction;

    @Column(name = "project_apply_reason")
    private String projectApplyReason;

    @Column(name = "project_apply_table")
    private String projectApplyTable;

    @Column(name = "project_planning_book")
    private String projectPlanningBook;

    @Column(name = "project_XFTGTJ")
    private String projectXFTGTJ;

    @Column(name = "project_expected_results")
    private String projectExpectedResults;

    @Column(name = "project_money_budgets")
    private String projectMoneyBudgets;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectOwnerId() {
        return projectOwnerId;
    }

    public void setProjectOwnerId(String projectOwnerId) {
        this.projectOwnerId = projectOwnerId;
    }

    public String getProjectOwnerName() {
        return projectOwnerName;
    }

    public void setProjectOwnerName(String projectOwnerName) {
        this.projectOwnerName = projectOwnerName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectKeyWords() {
        return projectKeyWords;
    }

    public void setProjectKeyWords(String projectKeyWords) {
        this.projectKeyWords = projectKeyWords;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectBelongSchool() {
        return projectBelongSchool;
    }

    public void setProjectBelongSchool(String projectBelongSchool) {
        this.projectBelongSchool = projectBelongSchool;
    }

    public String getProjectImplementationTime() {
        return projectImplementationTime;
    }

    public void setProjectImplementationTime(String projectImplementationTime) {
        this.projectImplementationTime = projectImplementationTime;
    }

    public String getProjectSSXKML() {
        return projectSSXKML;
    }

    public void setProjectSSXKML(String projectSSXKML) {
        this.projectSSXKML = projectSSXKML;
    }

    public String getProjectSSZYDL() {
        return projectSSZYDL;
    }

    public void setProjectSSZYDL(String projectSSZYDL) {
        this.projectSSZYDL = projectSSZYDL;
    }

    public String getProjectEstablishTime() {
        return projectEstablishTime;
    }

    public void setProjectEstablishTime(String projectEstablishTime) {
        this.projectEstablishTime = projectEstablishTime;
    }

    public String getProjectMembers() {
        return projectMembers;
    }

    public void setProjectMembers(String projectMembers) {
        this.projectMembers = projectMembers;
    }

    public String getProjectGuidanceTeacher() {
        return projectGuidanceTeacher;
    }

    public void setProjectGuidanceTeacher(String projectGuidanceTeacher) {
        this.projectGuidanceTeacher = projectGuidanceTeacher;
    }

    public String getProjectIntroduction() {
        return projectIntroduction;
    }

    public void setProjectIntroduction(String projectIntroduction) {
        this.projectIntroduction = projectIntroduction;
    }

    public String getProjectApplyReason() {
        return projectApplyReason;
    }

    public void setProjectApplyReason(String projectApplyReason) {
        this.projectApplyReason = projectApplyReason;
    }

    public String getProjectApplyTable() {
        return projectApplyTable;
    }

    public void setProjectApplyTable(String projectApplyTable) {
        this.projectApplyTable = projectApplyTable;
    }

    public String getProjectPlanningBook() {
        return projectPlanningBook;
    }

    public void setProjectPlanningBook(String projectPlanningBook) {
        this.projectPlanningBook = projectPlanningBook;
    }

    public String getProjectXFTGTJ() {
        return projectXFTGTJ;
    }

    public void setProjectXFTGTJ(String projectXFTGTJ) {
        this.projectXFTGTJ = projectXFTGTJ;
    }

    public String getProjectExpectedResults() {
        return projectExpectedResults;
    }

    public void setProjectExpectedResults(String projectExpectedResults) {
        this.projectExpectedResults = projectExpectedResults;
    }

    public String getProjectMoneyBudgets() {
        return projectMoneyBudgets;
    }

    public void setProjectMoneyBudgets(String projectMoneyBudgets) {
        this.projectMoneyBudgets = projectMoneyBudgets;
    }
}
