package com.tangtang.manager.service;

import com.tangtang.manager.dto.SchoolApplyDTO;
import com.tangtang.manager.dto.SchoolRegistrationDTO;
import com.tangtang.manager.pojo.BaseStudent;
import com.tangtang.manager.response.PageDataResult;

import java.util.Map;

public interface ApplyService {
    PageDataResult getSchoolApplyList(SchoolApplyDTO schoolApplyDTO, Integer pageNum, Integer pageSize);

    PageDataResult getStudentApplyList(BaseStudent student, Integer pageNum, Integer pageSize);

    Map<String,Object> confirmSchoolsApply(SchoolRegistrationDTO schoolRegistrationDTO);
}
