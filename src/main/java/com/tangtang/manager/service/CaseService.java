package com.tangtang.manager.service;

import com.tangtang.manager.dto.UserSearchDTO;
import com.tangtang.manager.pojo.BaseAdminUser;
import com.tangtang.manager.pojo.BaseSuccessfulCase;
import com.tangtang.manager.response.PageDataResult;

import java.util.List;
import java.util.Map;

public interface CaseService {
    Map<String,Object> addCase(BaseSuccessfulCase successfulCase);

    PageDataResult getCaseList(BaseSuccessfulCase successfulCase, Integer pageNum, Integer pageSize);

    List<BaseSuccessfulCase> getCaseShowData();

    Map<String, Object> del(long id);

    Map<String,Object> updateCase(BaseSuccessfulCase successfulCase);
}
