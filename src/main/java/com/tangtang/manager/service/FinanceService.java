package com.tangtang.manager.service;

import com.tangtang.manager.pojo.BaseFinance;
import com.tangtang.manager.pojo.BaseProjectDeclaration;
import com.tangtang.manager.response.PageDataResult;

import java.util.Map;

public interface FinanceService {
    Map<String,Object> addProjectFinance(BaseFinance finance);

    PageDataResult getProjectFinanceByProjectId(BaseFinance finance, Integer pageNum, Integer pageSize);

    Map<String, Object> del(long id);
}
