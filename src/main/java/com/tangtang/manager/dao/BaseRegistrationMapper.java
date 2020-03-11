package com.tangtang.manager.dao;

import com.tangtang.manager.dto.SchoolRegistrationDTO;
import com.tangtang.manager.dto.StudentRegistrationDTO;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

@Repository
public interface BaseRegistrationMapper extends MyMapper<StudentRegistrationDTO> {
    boolean registration(StudentRegistrationDTO studentRegistrationDTO);

    boolean regStudentInfo(StudentRegistrationDTO studentRegistrationDTO);

    boolean regSchoolUser(SchoolRegistrationDTO schoolRegistrationDTO);

    boolean confirmSchoolsApply(SchoolRegistrationDTO schoolRegistrationDTO);

    int updateSchoolInfo(SchoolRegistrationDTO schoolRegistrationDTO);
}
