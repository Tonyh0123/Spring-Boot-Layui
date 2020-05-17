package com.tangtang.manager.service;

import com.tangtang.manager.dto.*;
import com.tangtang.manager.pojo.BaseSuccessfulCase;
import com.tangtang.manager.response.PageDataResult;

import java.util.List;
import java.util.Map;

public interface DataAnalysisService {
    Map<String,Object> addCase(BaseSuccessfulCase successfulCase);

    PageDataResult getCaseList(BaseSuccessfulCase successfulCase, Integer pageNum, Integer pageSize);

    List<BaseSuccessfulCase> getCaseShowData(Integer pageNum, Integer pageSize);

    Map<String, Object> del(long id);

    Map<String,Object> updateCase(BaseSuccessfulCase successfulCase);

    Map<String,Object> platformMembers();

    List<InvestorProvinceDTO> investorProvince();

    List<CompanyTZProjectNumsDTO> CompanyTZProjectNums();

    List<InvestmentJDAvgMoneyDTO> InvestmentJDAvgMoney();

    List<InvestmentTypeDTO> InvestmentType();

    List<ProjectBelongSchoolDTO> ProjectBelongSchool();
}
