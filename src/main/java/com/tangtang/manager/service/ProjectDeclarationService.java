package com.tangtang.manager.service;

import com.tangtang.manager.pojo.BaseProjectDeclaration;
import com.tangtang.manager.response.PageDataResult;

import java.util.Map;

public interface ProjectDeclarationService {
    Map<String,Object> addProjectEstablishApply(BaseProjectDeclaration projectDeclaration);

    PageDataResult getProjectEstablishListForSchool(BaseProjectDeclaration projectDeclaration, Integer pageNum, Integer pageSize);

    PageDataResult getPersonalProjectEstablishList(BaseProjectDeclaration projectDeclaration, Integer pageNum, Integer pageSize);

    Map<String,Object> updateProjectDetail(BaseProjectDeclaration projectDeclaration);

    Map<String, Object> del(long id);
}
