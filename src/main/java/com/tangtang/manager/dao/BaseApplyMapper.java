package com.tangtang.manager.dao;

import com.tangtang.manager.dto.SchoolApplyDTO;
import com.tangtang.manager.pojo.BaseCompany;
import com.tangtang.manager.pojo.BaseStudent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseApplyMapper {
    List<SchoolApplyDTO> getSchoolApplyList(SchoolApplyDTO schoolApplyDTO, Integer pageNum, Integer pageSize);

    List<BaseStudent> getStudentApplyList(BaseStudent student, Integer pageNum, Integer pageSize);

    List<BaseCompany> getCompanyApplyList(BaseCompany company, Integer pageNum, Integer pageSize);

    boolean verifyStudentApply(BaseStudent student);
}
