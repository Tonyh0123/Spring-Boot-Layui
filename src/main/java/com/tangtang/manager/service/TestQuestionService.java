package com.tangtang.manager.service;

import com.tangtang.manager.dto.TestTargetScoreDTO;
import com.tangtang.manager.pojo.BaseSuccessfulCase;
import com.tangtang.manager.pojo.BaseTestQuestion;
import com.tangtang.manager.pojo.BaseTestResult;
import com.tangtang.manager.response.PageDataResult;

import java.util.List;
import java.util.Map;

public interface TestQuestionService {
    Map<String,Object> addTestQuestion(BaseTestQuestion question);

    Map<String,Object> updateTestResult(BaseTestResult baseTestResult);

    PageDataResult getTestQuestionList(BaseTestQuestion question, Integer pageNum, Integer pageSize);

    PageDataResult getTestResultListByUserId(BaseTestResult result, Integer pageNum, Integer pageSize);

    List<BaseTestQuestion> getTestQuestionList();

    List<BaseTestResult> getTestResultByUserId(String userId);

    List<BaseTestResult> getTestResultById(String recordId);

    Map<String, Object> del(long id);

    Map<String,Object> updateTestQuestion(BaseTestQuestion question);

    Map<String,Object> addSomethingToTestResult(BaseTestResult result);
}
