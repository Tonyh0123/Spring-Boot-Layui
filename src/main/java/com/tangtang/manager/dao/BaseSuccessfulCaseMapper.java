package com.tangtang.manager.dao;

import com.tangtang.manager.dto.SchoolApplyDTO;
import com.tangtang.manager.dto.SchoolRegistrationDTO;
import com.tangtang.manager.dto.StudentRegistrationDTO;
import com.tangtang.manager.pojo.BaseSuccessfulCase;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface BaseSuccessfulCaseMapper extends MyMapper<BaseSuccessfulCase> {
    boolean addCase(BaseSuccessfulCase successfulCase);

    List<BaseSuccessfulCase> getCaseList(BaseSuccessfulCase successfulCase);

    List<BaseSuccessfulCase> getCaseList();
}
