package com.tangtang.manager.dao;

import com.tangtang.manager.dto.SchoolApplyDTO;
import com.tangtang.manager.pojo.BaseStudent;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseApplyMapper {
    List<SchoolApplyDTO> getSchoolApplyList(SchoolApplyDTO schoolApplyDTO);

    List<BaseStudent> getStudentApplyList(BaseStudent student);
}
