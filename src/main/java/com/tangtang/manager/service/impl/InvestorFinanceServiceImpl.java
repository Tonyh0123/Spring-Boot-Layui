package com.tangtang.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangtang.manager.dao.BaseFinanceMapper;
import com.tangtang.manager.dao.BaseInvestorFinanceMapper;
import com.tangtang.manager.pojo.BaseFinance;
import com.tangtang.manager.pojo.BaseInvestorFinance;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.FinanceService;
import com.tangtang.manager.service.InvestorFinanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvestorFinanceServiceImpl implements InvestorFinanceService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseInvestorFinanceMapper investorFinanceMapper;

    @Override
    public Map<String,Object> addInvestment(BaseInvestorFinance investorFinance) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = investorFinanceMapper.addInvestment(investorFinance);
            if(!result){
                data.put("code",0);
                data.put("msg","操作失败，请联系管理！");
                logger.error("新增融资信息，结果=新增失败，数据插入异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增成功！");
            logger.info("新增融资信息，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增融资信息异常！", e);
            return data;
        }
        return data;
    }


    @Override
    public PageDataResult getInvestmentByUserId(BaseInvestorFinance investorFinance, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<BaseInvestorFinance> investorFinances = investorFinanceMapper.getInvestmentByUserId(investorFinance);
        PageHelper.startPage(pageNum, pageSize);
        if(investorFinances.size() != 0){
            PageInfo<BaseInvestorFinance> pageInfo = new PageInfo<>(investorFinances);
            pageDataResult.setList(investorFinances);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }
        return pageDataResult;
    }

    @Override
    public PageDataResult getInvestments(BaseInvestorFinance investorFinance, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        pageNum = (pageNum-1)*pageSize;
        List<BaseInvestorFinance> nums = investorFinanceMapper.getInvestments(investorFinance,0,10000);
        List<BaseInvestorFinance> investorFinances = investorFinanceMapper.getInvestments(investorFinance,pageNum,pageSize);

        if(investorFinances.size() != 0){
            pageDataResult.setList(investorFinances);
            pageDataResult.setTotals(nums.size());
        }
        return pageDataResult;
    }

    @Override
    public Map<String, Object> updateInvestment(BaseInvestorFinance investorFinance) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = investorFinanceMapper.updateInvestment(investorFinance);
            if(!result){
                data.put("code",0);
                data.put("msg","操作失败，请联系管理！");
                logger.error("更新融资信息，结果=失败，数据插入异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","更新成功！");
            logger.info("更新融资信息，结果=成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("更新融资信息异常！", e);
            return data;
        }
        return data;
    }


    @Override
    public Map<String, Object> del(long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = investorFinanceMapper.deleteByPrimaryKey(id);
            if(result == 0){
                data.put("code",0);
                data.put("msg","删除失败");
                logger.error("删除失败");
                return data;
            }
            data.put("code",1);
            data.put("msg","删除成功");
            logger.info("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除融资信息异常！", e);
        }
        return data;
    }

}
