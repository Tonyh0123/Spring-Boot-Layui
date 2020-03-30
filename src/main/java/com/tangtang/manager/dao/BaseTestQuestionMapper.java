package com.tangtang.manager.dao;

import com.tangtang.manager.dto.TestTargetScoreDTO;
import com.tangtang.manager.pojo.BaseSuccessfulCase;
import com.tangtang.manager.pojo.BaseTestQuestion;
import com.tangtang.manager.pojo.BaseTestResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface BaseTestQuestionMapper extends MyMapper<BaseTestQuestion> {
    boolean addTestQuestion(BaseTestQuestion question);

    boolean addTestResult(BaseTestResult baseTestResult);

    boolean addSomethingToTestResult(BaseTestResult baseTestResult);

    List<BaseTestQuestion> getTestQuestionList(BaseTestQuestion question);

    List<BaseTestQuestion> getTestQuestionList();

    List<BaseTestResult> getTestResultById(@Param("userId") String userId);

    int updateTestQuestion(BaseTestQuestion question);
}
