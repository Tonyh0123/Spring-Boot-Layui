package com.tangtang.manager.service;

import com.tangtang.manager.pojo.BaseSuccessfulCase;
import com.tangtang.manager.pojo.BaseTestQuestion;
import com.tangtang.manager.response.PageDataResult;

import java.util.List;
import java.util.Map;

public interface TestQuestionService {
    Map<String,Object> addTestQuestion(BaseTestQuestion question);

    PageDataResult getTestQuestionList(BaseTestQuestion question, Integer pageNum, Integer pageSize);

    List<BaseTestQuestion> getTestQuestionList();

    Map<String, Object> del(long id);

    Map<String,Object> updateTestQuestion(BaseTestQuestion question);
}
