package com.tangtang.manager.dao;

import com.tangtang.manager.dto.CompanyRegistrationDTO;
import com.tangtang.manager.dto.SchoolRegistrationDTO;
import com.tangtang.manager.dto.StudentRegistrationDTO;
import com.tangtang.manager.pojo.BaseCompany;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

@Repository
public interface BaseRegistrationMapper extends MyMapper<StudentRegistrationDTO> {
    boolean registration(StudentRegistrationDTO studentRegistrationDTO);

    boolean regStudentInfo(StudentRegistrationDTO studentRegistrationDTO);

    boolean regSchoolUser(SchoolRegistrationDTO schoolRegistrationDTO);

    boolean regCompanyUser(BaseCompany company);

    boolean confirmSchoolsApply(SchoolRegistrationDTO schoolRegistrationDTO);

    /**
     * 同意企业申请时为企业用户创建账号
     * @param companyRegistrationDTO
     * @return
     */
    boolean confirmCompanyApply(CompanyRegistrationDTO companyRegistrationDTO);

    int updateSchoolInfo(SchoolRegistrationDTO schoolRegistrationDTO);

    int updateCompanyInfo(CompanyRegistrationDTO companyRegistrationDTO);
}
