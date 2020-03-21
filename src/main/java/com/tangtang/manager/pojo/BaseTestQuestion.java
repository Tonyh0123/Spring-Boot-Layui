package com.tangtang.manager.pojo;

import javax.persistence.*;

@Table(name = "base_test_question")
public class BaseTestQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "test_question")
    private String testQuestion;

    @Column(name = "test_answerA")
    private String testAnswerA;

    @Column(name = "test_answerB")
    private String testAnswerB;

    @Column(name = "test_answerC")
    private String testAnswerC;

    @Column(name = "test_answerD")
    private String testAnswerD;

    @Column(name = "test_answerA_score")
    private String testAnswerA_score;

    @Column(name = "test_answerB_score")
    private String testAnswerB_score;

    @Column(name = "test_answerC_score")
    private String testAnswerC_score;

    @Column(name = "test_answerD_score")
    private String testAnswerD_score;

    @Column(name = "test_type")
    private String testType;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestQuestion() {
        return testQuestion;
    }

    public void setTestQuestion(String testQuestion) {
        this.testQuestion = testQuestion;
    }

    public String getTestAnswerA() {
        return testAnswerA;
    }

    public void setTestAnswerA(String testAnswerA) {
        this.testAnswerA = testAnswerA;
    }

    public String getTestAnswerB() {
        return testAnswerB;
    }

    public void setTestAnswerB(String testAnswerB) {
        this.testAnswerB = testAnswerB;
    }

    public String getTestAnswerC() {
        return testAnswerC;
    }

    public void setTestAnswerC(String testAnswerC) {
        this.testAnswerC = testAnswerC;
    }

    public String getTestAnswerD() {
        return testAnswerD;
    }

    public void setTestAnswerD(String testAnswerD) {
        this.testAnswerD = testAnswerD;
    }

    public String getTestAnswerA_score() {
        return testAnswerA_score;
    }

    public void setTestAnswerA_score(String testAnswerA_score) {
        this.testAnswerA_score = testAnswerA_score;
    }

    public String getTestAnswerB_score() {
        return testAnswerB_score;
    }

    public void setTestAnswerB_score(String testAnswerB_score) {
        this.testAnswerB_score = testAnswerB_score;
    }

    public String getTestAnswerC_score() {
        return testAnswerC_score;
    }

    public void setTestAnswerC_score(String testAnswerC_score) {
        this.testAnswerC_score = testAnswerC_score;
    }

    public String getTestAnswerD_score() {
        return testAnswerD_score;
    }

    public void setTestAnswerD_score(String testAnswerD_score) {
        this.testAnswerD_score = testAnswerD_score;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }
}
