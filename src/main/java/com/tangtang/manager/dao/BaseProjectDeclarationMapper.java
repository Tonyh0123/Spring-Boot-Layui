package com.tangtang.manager.dao;

import com.tangtang.manager.pojo.BaseProjectDeclaration;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;


@Repository
public interface BaseProjectDeclarationMapper extends MyMapper<BaseProjectDeclaration> {
    boolean addProjectEstablishApply(BaseProjectDeclaration projectDeclaration);

    String getSchoolNameByUserId(String userId);

    List<BaseProjectDeclaration> getProjectEstablishListForSchool(BaseProjectDeclaration projectDeclaration);

    List<BaseProjectDeclaration> getPersonalProjectEstablishList(BaseProjectDeclaration projectDeclaration);

    /**
     * 学校审核学生立项申请时为学生安排立项答辩时间，并更新相应表字段
     * @param projectDeclaration
     * @return
     */
    boolean updateProjectDetail(BaseProjectDeclaration projectDeclaration);

    /**
     * 通过立项答辩之后学校予以立项，立项时校方填写学校可为该项目提供怎样的支持，也就是校方提供条件。
     * @param projectDeclaration
     * @return
     */
    boolean agreeTheLXApply(BaseProjectDeclaration projectDeclaration);

    /**
     * 状态为‘已立项’的项目只能申请变更创新阶段，状态为‘创新阶段’的项目可进一步申请‘成长阶段’
     * 此方法为检查项目的当前状态，判断项目能申请的阶段
     * @param projectDeclaration
     * @return
     */
    String checkCurrentJD(BaseProjectDeclaration projectDeclaration);

    /**
     * 学校方面规定一学年有两次创业阶段答辩的机会，而这些机会需要项目发起人在平台发起‘项目阶段变更答辩申请’
     * 学生在申请阶段变更时需提交一些材料，供答辩组审阅。具体是什么材料由学生决定。
     * @param projectDeclaration
     * @return
     */
    boolean JDBGApply(BaseProjectDeclaration projectDeclaration);

    /**
     * 学校方面需在平台上统一为已申请阶段变更的项目进行答辩时间安排
     * @param projectDeclaration
     * @return
     */
    boolean setTimeOfDB(BaseProjectDeclaration projectDeclaration);

    /**
     * 答辩之后经答辩组一致同意之后校方予以项目阶段变更，学校在平台为已通过答辩的项目进行阶段变更
     * @param projectDeclaration
     * @return
     */
    boolean setCurrentJD(BaseProjectDeclaration projectDeclaration);

    List<BaseProjectDeclaration> getLXProjectListForSchool(BaseProjectDeclaration projectDeclaration);

    /**
     * 学校可根据答辩情况在平台上向企业推荐相应项目
     * @param projectDeclaration
     * @return
     */
    boolean recommendOrNot(BaseProjectDeclaration projectDeclaration);
}
