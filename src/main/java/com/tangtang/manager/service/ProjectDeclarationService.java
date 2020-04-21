package com.tangtang.manager.service;

import com.tangtang.manager.pojo.BaseProjectDeclaration;
import com.tangtang.manager.response.PageDataResult;

import java.util.Map;

public interface ProjectDeclarationService {
    Map<String,Object> addProjectEstablishApply(BaseProjectDeclaration projectDeclaration);

    String getSchoolNameByUserId(String userId);

    PageDataResult getProjectEstablishListForSchool(BaseProjectDeclaration projectDeclaration, Integer pageNum, Integer pageSize);

    PageDataResult getPersonalProjectEstablishList(BaseProjectDeclaration projectDeclaration, Integer pageNum, Integer pageSize);

    PageDataResult getLXProjectList(BaseProjectDeclaration projectDeclaration, Integer pageNum, Integer pageSize);


    Map<String,Object> updateProjectDetail(BaseProjectDeclaration projectDeclaration);

    Map<String,Object> agreeTheLXApply(BaseProjectDeclaration projectDeclaration);

    Map<String,Object> JDBGApply(BaseProjectDeclaration projectDeclaration);

    Map<String,Object> setTimeOfDB(BaseProjectDeclaration projectDeclaration);

    Map<String,Object> setCurrentJD(BaseProjectDeclaration projectDeclaration);

    PageDataResult getLXProjectListForSchool(BaseProjectDeclaration projectDeclaration, Integer pageNum, Integer pageSize);

    Map<String,Object> recommendOrNot(BaseProjectDeclaration projectDeclaration);

    String checkCurrentJD(BaseProjectDeclaration projectDeclaration);

    Map<String, Object> del(long id);
}
