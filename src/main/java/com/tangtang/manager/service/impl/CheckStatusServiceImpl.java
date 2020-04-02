package com.tangtang.manager.service.impl;

import com.tangtang.manager.dao.BaseCheckStatusMapper;
import com.tangtang.manager.pojo.BaseStudent;
import com.tangtang.manager.service.CheckStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CheckStatusServiceImpl implements CheckStatusService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseCheckStatusMapper checkStatusMapper;


    /**
     * 获取学生用户核验状态
     * @param userId
     * @return
     */
    @Override
    public List<BaseStudent> getStudentStatus(String userId) {
        return checkStatusMapper.getStudentStatus(userId);
    }


    /**
     * 学生提交身份审核信息，更新学生表信息、学生身份状态信息更新
     * @param student
     * @return
     */
    @Override
    public Map<String, Object> updateBaseStudent(BaseStudent student) {
        Map<String,Object> data = new HashMap();
        try{
        boolean result = checkStatusMapper.updateBaseStudent(student);
        if(!result){
            data.put("code",0);
            data.put("msg","更新失败！");
            logger.error("学生信息[更新]，结果=更新失败！");
            return data;
        }
        data.put("code",1);
        data.put("msg","更新成功！");
        logger.info("学生信息[更新]，结果=更新成功！");
        }catch (Exception e){
            System.out.println(e);
        }
        return data;
    }

}
