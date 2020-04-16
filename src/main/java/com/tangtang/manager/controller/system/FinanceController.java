package com.tangtang.manager.controller.system;

import com.tangtang.manager.common.utils.DateUtils;
import com.tangtang.manager.pojo.BaseFinance;
import com.tangtang.manager.pojo.BaseProjectDeclaration;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.FinanceService;
import com.tangtang.manager.service.ProjectDeclarationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @Title: FinanceController
 * @Description: 项目融资管理
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/04/15 11：57
 */
@Controller
@RequestMapping("finance")
public class FinanceController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FinanceService financeService;

    /**
     *
     * 功能描述: 查询项目融资列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/15 12：05
     */
    @RequestMapping(value = "/getProjectFinanceByProjectId", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getProjectFinanceByProjectId(@RequestParam("pageNum") Integer pageNum,
                                                       @RequestParam("pageSize") Integer pageSize, BaseFinance finance, String projectId) {
        finance.setProject_id(projectId);
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取列表
            pdr = financeService.getProjectFinanceByProjectId(finance, pageNum ,pageSize);
            logger.info("项目融资列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("项目融资列表查询异常！", e);
        }
        return pdr;
    }

    /**
     * 新增项目融资条目
     * @param finance
     * @return
     */
    @RequestMapping(value = "/addProjectFinance", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addProjectFinance(BaseFinance finance) {
        Map<String,Object> data = new HashMap();
        logger.info("新增项目融资条目");
        data = financeService.addProjectFinance(finance);
        return data;
    }

    /**
     * 删除项目融资
     * @Author tangtang
     * @param id
     * @return
     */
    @PostMapping("del")
    @ResponseBody
    public Map<String, Object> del(@RequestParam("id") Long id) {
        logger.info("删除项目融资！id:" + id);
        Map<String, Object> data = new HashMap<>();
        data = financeService.del(id);
        return data;
    }



}
