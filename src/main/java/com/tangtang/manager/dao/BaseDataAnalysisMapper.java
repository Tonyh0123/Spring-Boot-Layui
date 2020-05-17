package com.tangtang.manager.dao;

import com.tangtang.manager.dto.*;
import com.tangtang.manager.pojo.BaseTestQuestion;
import com.tangtang.manager.pojo.BaseTestResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface BaseDataAnalysisMapper extends MyMapper<BaseTestQuestion> {

    Integer studentNums();

    Integer schoolNums();

    Integer companyNums();

    List<InvestorProvinceDTO> investorProvince();

    List<CompanyTZProjectNumsDTO> CompanyTZProjectNums();

    List<InvestmentJDAvgMoneyDTO> InvestmentJDAvgMoney();

    List<InvestmentTypeDTO> InvestmentType();

    List<ProjectBelongSchoolDTO> ProjectBelongSchool();

}
