package com.tangtang.manager.controller.system;

import com.tangtang.manager.pojo.BaseFinance;
import com.tangtang.manager.pojo.BaseInvestorFinance;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.FinanceService;
import com.tangtang.manager.service.InvestorFinanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: InvestorFinanceController
 * @Description: 融资信息管理
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/04/15 17：57
 */
@Controller
@RequestMapping("investorFinance")
public class InvestorFinanceController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InvestorFinanceService investorFinanceService;

    /**
     *
     * 功能描述: 跳到个人发布融资列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/15 11：58
     */
    @RequestMapping("/personalInvestment")
    public String userManage() {
        return "/investorFinance/personalInvestment";
    }

    /**
     *
     * 功能描述: 查询个人发布融资
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/15 17：05
     */
    @RequestMapping(value = "/getInvestmentByUserId", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getInvestmentByUserId(@RequestParam("pageNum") Integer pageNum,
                                                @RequestParam("pageSize") Integer pageSize, BaseInvestorFinance investorFinance, String userId) {
        investorFinance.setInvestor_id(userId);
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取列表
            pdr = investorFinanceService.getInvestmentByUserId(investorFinance, pageNum ,pageSize);
            logger.info("个人发布融资信息查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("个人发布融资信息查询异常！", e);
        }
        return pdr;
    }

    /**
     *
     * 功能描述: 查询所有已发布融资信息
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/15 17：05
     */
    @RequestMapping(value = "/getInvestments", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getInvestments(@RequestParam("pageNum") Integer pageNum,
                                                @RequestParam("pageSize") Integer pageSize, BaseInvestorFinance investorFinance) {
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取列表
            pdr = investorFinanceService.getInvestments(investorFinance, pageNum ,pageSize);
            logger.info("所有融资信息查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("所有融资信息查询异常！", e);
        }
        return pdr;
    }

    /**
     * 发布融资信息
     * @param investorFinance
     * @return
     */
    @RequestMapping(value = "/addInvestment", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addInvestment(BaseInvestorFinance investorFinance) {
        Map<String,Object> data = new HashMap();
        if(investorFinance.getId() == null){
            logger.info("发布融资信息");
            data = investorFinanceService.addInvestment(investorFinance);
        }else {
            logger.info("更新融资信息");
            data = investorFinanceService.updateInvestment(investorFinance);
        }

        return data;
    }

    /**
     * 更新融资信息
     * @param investorFinance
     * @return
     */
    @RequestMapping(value = "/updateInvestment", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateInvestment(BaseInvestorFinance investorFinance) {
        Map<String,Object> data = new HashMap();
        logger.info("更新融资信息");
        data = investorFinanceService.updateInvestment(investorFinance);
        return data;
    }

    /**
     * 删除融资信息
     * @Author tangtang
     * @param id
     * @return
     */
    @PostMapping("del")
    @ResponseBody
    public Map<String, Object> del(@RequestParam("id") Long id) {
        logger.info("删除融资信息！id:" + id);
        Map<String, Object> data = new HashMap<>();
        data = investorFinanceService.del(id);
        return data;
    }



}
