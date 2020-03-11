package com.tangtang.manager.dao;

import com.tangtang.manager.dto.SchoolApplyDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseApplyMapper {
    List<SchoolApplyDTO> getSchoolApplyList(SchoolApplyDTO schoolApplyDTO);
}
