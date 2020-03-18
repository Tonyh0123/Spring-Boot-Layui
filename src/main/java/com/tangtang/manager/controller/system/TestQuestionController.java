package com.tangtang.manager.controller.system;

import com.tangtang.manager.dto.SchoolRegistrationDTO;
import com.tangtang.manager.dto.StudentRegistrationDTO;
import com.tangtang.manager.pojo.BaseAdminUser;
import com.tangtang.manager.pojo.BaseSuccessfulCase;
import com.tangtang.manager.pojo.BaseTestQuestion;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.AdminUserService;
import com.tangtang.manager.service.CaseService;
import com.tangtang.manager.service.TestQuestionService;
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
 * @Description: 测试题目管理
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/03/17 11：57
 */
@Controller
@RequestMapping("testQuestion")
public class TestQuestionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestQuestionService testQuestionService;


    /**
     *
     * 功能描述: 跳到测试题目列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/17 11：58
     */
    @RequestMapping("/testQuestionManage")
    public String userManage() {
        return "/testQuestion/testQuestionManage";
    }


    /**
     *
     * 功能描述: 分页查询测试题目列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/17 11：59
     */
    @RequestMapping(value = "/getTestQuestionList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getUserList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,/*@Valid PageRequest page,*/ BaseTestQuestion question) {
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
            pdr = testQuestionService.getTestQuestionList(question, pageNum ,pageSize);
            logger.info("题目列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("题目列表查询异常！", e);
        }
        return pdr;
    }

    /**
     *
     * 功能描述: 获取测试题目数据
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/18 16:28
     */
    @GetMapping("getTestPaper")
    public @ResponseBody List<BaseTestQuestion> getCaseShowData(){
        logger.info("获取测试题目数据");
        return testQuestionService.getTestQuestionList();
    }

    /**
     * 新增测试题目
     * @param testQuestion
     * @return
     */
    @RequestMapping(value = "/addTestQuestion", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addCase(BaseTestQuestion testQuestion) {

        Map<String,Object> data = new HashMap();
        if(testQuestion.getId() == null){
            logger.info("新增测试题目 ------> successfulCase:" + testQuestion);
            data = testQuestionService.addTestQuestion(testQuestion);
        }else{
            logger.info("编辑测试题目 ------> successfulCase:" + testQuestion);
            data = testQuestionService.updateTestQuestion(testQuestion);
        }

        return data;
    }


    /**
     * 删除测试题目
     * @Author tangtang
     * @param id
     * @return
     */
    @PostMapping("del")
    @ResponseBody
    public Map<String, Object> del(@RequestParam("id") Long id) {
        logger.info("删除测试题目！id:" + id);
        Map<String, Object> data = new HashMap<>();
        data = testQuestionService.del(id);
        return data;
    }


}
