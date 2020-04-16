package com.tangtang.manager.dao;

import com.tangtang.manager.pojo.BaseFinance;
import com.tangtang.manager.pojo.BaseInvestorFinance;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;


@Repository
public interface BaseInvestorFinanceMapper extends MyMapper<BaseInvestorFinance> {
    boolean addInvestment(BaseInvestorFinance investorFinance);

    List<BaseInvestorFinance> getInvestmentByUserId(BaseInvestorFinance investorFinance);

    List<BaseInvestorFinance> getInvestments(BaseInvestorFinance investorFinance,Integer pageNum, Integer pageSize);

    boolean updateInvestment(BaseInvestorFinance investorFinance);

}
