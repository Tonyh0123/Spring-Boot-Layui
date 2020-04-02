package com.tangtang.manager.controller.system;

import com.tangtang.manager.dto.LoginDTO;
import com.tangtang.manager.dto.SchoolRegistrationDTO;
import com.tangtang.manager.dto.StudentRegistrationDTO;
import com.tangtang.manager.dto.UserSearchDTO;
import com.tangtang.manager.pojo.BaseAdminUser;
import com.tangtang.manager.pojo.BaseStudent;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.AdminUserService;
import com.tangtang.manager.service.CheckStatusService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: CheckStatusController
 * @Description: 用户状态检查
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/04/01 15:17
 */
@Controller
@RequestMapping("check")
public class CheckStatusController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CheckStatusService checkStatusService;

    /**
     *
     * 功能描述: 获取学生用户核验状态
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/10 11:59
     */
    @RequestMapping(value = "/checkStudentStatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> checkStudentStatus(@RequestParam("userId") String userId, HttpServletResponse response) {
        logger.info("获取学生用户核验状态！");
        List<BaseStudent> students = checkStatusService.getStudentStatus(userId);
        BaseStudent student = students.get(students.size()-1);
        Cookie cookie=new Cookie("studentStatus",student.getStudentStatus());
        cookie.setMaxAge(24*60*60);       //存活一天
        cookie.setPath("/");
        response.addCookie(cookie);
        Map<String, Object> data = new HashMap<>();
        data.put("msg","cookies添加成功");
        data.put("studentStatus",student.getStudentStatus());
        data.put("cookies",cookie.toString());
        return data;
    }

    /**
     *
     * 功能描述: 学生提交审核文件信息，更新数据库中表信息
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/02 11:59
     */
    @RequestMapping(value = "/updateBaseStudent", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateBaseStudent(BaseStudent student) {
        logger.info("学生提交审核文件信息");
        student.setStudentStatus("2");  //学生身份状态--> 0(未审核)  1(审核通过)  2(审核中)
        Map<String, Object> data = checkStatusService.updateBaseStudent(student);
        return data;
    }

    /**
     *
     * 功能描述: 学生信息审核通过/不通过  更新学生身份审核信息
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/02 12:20
     */
    @RequestMapping(value = "/updateStudentStatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateStudentStatus(@RequestParam("userId") String userId, @RequestParam("accept") String accept) {
        logger.info("学生提交审核文件信息");
        BaseStudent student = new BaseStudent();
        student.setId(Integer.parseInt(userId));
        student.setStudentStatus(accept);  //学生身份状态--> 0(未审核)  1(审核通过)  2(审核中)
        Map<String, Object> data = checkStatusService.updateBaseStudent(student);
        return data;
    }






}
