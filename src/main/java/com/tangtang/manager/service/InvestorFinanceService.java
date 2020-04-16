package com.tangtang.manager.service;

import com.tangtang.manager.pojo.BaseFinance;
import com.tangtang.manager.pojo.BaseInvestorFinance;
import com.tangtang.manager.response.PageDataResult;

import java.util.Map;

public interface InvestorFinanceService {
    Map<String,Object> addInvestment(BaseInvestorFinance investorFinance);

    PageDataResult getInvestmentByUserId(BaseInvestorFinance investorFinance, Integer pageNum, Integer pageSize);

    PageDataResult getInvestments(BaseInvestorFinance investorFinance, Integer pageNum, Integer pageSize);

    Map<String,Object> updateInvestment(BaseInvestorFinance investorFinance);

    Map<String, Object> del(long id);
}
