package com.tangtang.manager.dao;

import com.tangtang.manager.pojo.BaseSuccessfulCase;
import com.tangtang.manager.pojo.BaseTestQuestion;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface BaseTestQuestionMapper extends MyMapper<BaseTestQuestion> {
    boolean addTestQuestion(BaseTestQuestion question);

    List<BaseTestQuestion> getTestQuestionList(BaseTestQuestion question);

    List<BaseTestQuestion> getTestQuestionList();

    int updateTestQuestion(BaseTestQuestion question);
}
