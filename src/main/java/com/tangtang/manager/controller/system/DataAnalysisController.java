package com.tangtang.manager.controller.system;

import com.tangtang.manager.pojo.BaseSuccessfulCase;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.AdminUserService;
import com.tangtang.manager.service.CaseService;
import com.tangtang.manager.service.DataAnalysisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: CaseController
 * @Description: 数据统计分析
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/05/17 11：57
 */
@Controller
@RequestMapping("analysis")
public class DataAnalysisController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private CaseService caseService;

    @Autowired
    private DataAnalysisService analysisService;


    /**
     *
     * 功能描述: 跳到成功案例列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/03 11：58
     */
    @RequestMapping("/caseManage")
    public String userManage() {
        return "/case/caseManage";
    }

    /**
     *
     * 功能描述: 跳到案例展示界面
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/03 11：58
     */
    @RequestMapping("/caseShow")
    public String caseShow() {
        return "/case/caseShow";
    }

    /**
     *
     * 功能描述: 获取案例展示数据
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/06 16:28
     */
    @GetMapping("caseShowData")
    public @ResponseBody List<BaseSuccessfulCase> getCaseShowData(Integer pageNum,Integer pageSize){

        logger.info("获取案例展示数据");
        if(null == pageNum) {
            pageNum = 1;
        }
        if(null == pageSize) {
            pageSize = 10;
        }
        return caseService.getCaseShowData(pageNum,pageSize);
    }

    /**
     *
     * 功能描述: 分页查询成功案例列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/03 11：59
     */
    @RequestMapping(value = "/getCaseList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getUserList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,/*@Valid PageRequest page,*/ BaseSuccessfulCase successfulCase) {
        /*logger.info("分页查询用户列表！搜索条件：userSearch：" + userSearch + ",pageNum:" + page.getPageNum()
                + ",每页记录数量pageSize:" + page.getPageSize());*/
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取用户列表
            pdr = caseService.getCaseList(successfulCase, pageNum ,pageSize);
            logger.info("案例列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("案例列表查询异常！", e);
        }
        return pdr;
    }

    /**
     * 新增成功案例
     * @param successfulCase
     * @return
     */
    @RequestMapping(value = "/addCase", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addCase(BaseSuccessfulCase successfulCase) {

        Map<String,Object> data = new HashMap();
        if(successfulCase.getId() == null){
            logger.info("新增成功案例 ------> successfulCase:" + successfulCase);
            data = caseService.addCase(successfulCase);
        }else{
            logger.info("编辑成功案例 ------> successfulCase:" + successfulCase);
            data = caseService.updateCase(successfulCase);
        }

        return data;
    }


    /**
     * 删除成功案例
     * @Author tangtang
     * @param id
     * @return
     */
    @PostMapping("del")
    @ResponseBody
    public Map<String, Object> del(@RequestParam("id") Long id) {
        logger.info("删除成功案例！id:" + id);
        Map<String, Object> data = new HashMap<>();
        data = caseService.del(id);
        return data;
    }

    @RequestMapping(value = "/platformMembers", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> platformMembers() {
        Map<String,Object> data = new HashMap();
        data = analysisService.platformMembers();
        return data;
    }

    @RequestMapping(value = "/investorProvince", method = RequestMethod.POST)
    @ResponseBody
    public List investorProvince() {
        return analysisService.investorProvince();
    }

    @RequestMapping(value = "/CompanyTZProjectNums", method = RequestMethod.POST)
    @ResponseBody
    public List CompanyTZProjectNums() {
        return analysisService.CompanyTZProjectNums();
    }

    @RequestMapping(value = "/InvestmentJDAvgMoney", method = RequestMethod.POST)
    @ResponseBody
    public List InvestmentJDAvgMoney() {
        return analysisService.InvestmentJDAvgMoney();
    }

    @RequestMapping(value = "/InvestmentType", method = RequestMethod.POST)
    @ResponseBody
    public List InvestmentType() {
        return analysisService.InvestmentType();
    }

    @RequestMapping(value = "/ProjectBelongSchool", method = RequestMethod.POST)
    @ResponseBody
    public List ProjectBelongSchool() {
        return analysisService.ProjectBelongSchool();
    }

}
