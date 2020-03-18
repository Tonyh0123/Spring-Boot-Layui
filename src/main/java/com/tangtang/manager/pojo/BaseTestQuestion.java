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

    @Column(name = "test_correct_answer")
    private String testCorrectAnswer;

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

    public String getTestCorrectAnswer() {
        return testCorrectAnswer;
    }

    public void setTestCorrectAnswer(String testCorrectAnswer) {
        this.testCorrectAnswer = testCorrectAnswer;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }
}
