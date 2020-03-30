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

    Map<String,Object> addTestResult(BaseTestResult baseTestResult);

    PageDataResult getTestQuestionList(BaseTestQuestion question, Integer pageNum, Integer pageSize);

    List<BaseTestQuestion> getTestQuestionList();

    List<BaseTestResult> getTestResultById(String userId);

    Map<String, Object> del(long id);

    Map<String,Object> updateTestQuestion(BaseTestQuestion question);

    Map<String,Object> addSomethingToTestResult(BaseTestResult result);
}
