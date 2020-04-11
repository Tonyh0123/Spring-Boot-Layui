package com.tangtang.manager.dao;

import com.tangtang.manager.pojo.BaseProjectDeclaration;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;


@Repository
public interface BaseProjectDeclarationMapper extends MyMapper<BaseProjectDeclaration> {
    boolean addProjectEstablishApply(BaseProjectDeclaration projectDeclaration);

    List<BaseProjectDeclaration> getProjectEstablishListForSchool(BaseProjectDeclaration projectDeclaration);

    List<BaseProjectDeclaration> getPersonalProjectEstablishList(BaseProjectDeclaration projectDeclaration);

    boolean updateProjectDetail(BaseProjectDeclaration projectDeclaration);
}
