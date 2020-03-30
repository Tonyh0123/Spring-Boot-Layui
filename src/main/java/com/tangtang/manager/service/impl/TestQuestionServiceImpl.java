package com.tangtang.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangtang.manager.common.utils.DateUtils;
import com.tangtang.manager.dao.BaseTestQuestionMapper;
import com.tangtang.manager.dto.TestTargetScoreDTO;
import com.tangtang.manager.pojo.BaseTestQuestion;
import com.tangtang.manager.pojo.BaseTestResult;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.TestQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestQuestionServiceImpl implements TestQuestionService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseTestQuestionMapper questionMapper;

    @Override
    public Map<String,Object> addTestQuestion(BaseTestQuestion question) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = questionMapper.addTestQuestion(question);
            if(!result){
                data.put("code",0);
                data.put("msg","操作失败，请联系管理！");
                logger.error("新增测试题目，结果=新增失败，数据插入异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增成功！");
            logger.info("新增测试题目，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增测试题目异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public Map<String, Object> addTestResult(BaseTestResult baseTestResult) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = questionMapper.addTestResult(baseTestResult);
            if(!result){
                data.put("code",0);
                data.put("msg","操作失败，请联系管理！");
                logger.error("新增测试结果，结果=新增失败，数据插入异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增成功！");
            logger.info("新增测试结果，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增测试结果异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public PageDataResult getTestQuestionList(BaseTestQuestion question, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<BaseTestQuestion> baseQuestions = questionMapper.getTestQuestionList(question);
        PageHelper.startPage(pageNum, pageSize);
        if(baseQuestions.size() != 0){
            PageInfo<BaseTestQuestion> pageInfo = new PageInfo<>(baseQuestions);
            pageDataResult.setList(baseQuestions);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }

    @Override
    public List<BaseTestQuestion> getTestQuestionList() {
        return questionMapper.getTestQuestionList();
    }

    @Override
    public List<BaseTestResult> getTestResultById(String userId) {
        return questionMapper.getTestResultById(userId);
    }

    @Override
    public Map<String, Object> del(long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            // 删除权限菜单
            int result = questionMapper.deleteByPrimaryKey(id);
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
            logger.error("删除测试题目异常！", e);
        }
        return data;
    }

    @Override
    public Map<String,Object> updateTestQuestion(BaseTestQuestion question) {
        Map<String,Object> data = new HashMap();
        Integer id = question.getId();
        int result = questionMapper.updateTestQuestion(question);
        if(result == 0){
            data.put("code",0);
            data.put("msg","更新失败！");
            logger.error("题目[更新]，结果=更新失败！");
            return data;
        }
        data.put("code",1);
        data.put("msg","更新成功！");
        logger.info("题目[更新]，结果=更新成功！");
        return data;
    }

    @Override
    public Map<String, Object> addSomethingToTestResult(BaseTestResult baseTestResult) {
        Map<String,Object> data = new HashMap();
        try {
            baseTestResult.setStartTime(DateUtils.getCurrentDate());
            boolean result = questionMapper.addSomethingToTestResult(baseTestResult);
            if(!result){
                data.put("msg","操作失败，请联系管理！");
                logger.error("测试结果数据插入，结果=失败！");
                return data;
            }
            String currentTime = DateUtils.getCurrentDate();
            String startTime = baseTestResult.getStartTime();
            String leftTime = DateUtils.getLeftTime(currentTime,startTime);
            int recordId = baseTestResult.getId();
            data.put("recordId",recordId); //测试结果的id
            data.put("leftTime",leftTime);
            logger.info("测试结果数据插入，结果=成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("测试结果数据插入异常！", e);
            return data;
        }
        return data;
    }
}
