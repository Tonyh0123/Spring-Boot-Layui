package com.tangtang.manager.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Title: LoginController
 * @Description:
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/03/09 11:39
 */
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());



    @RequestMapping("registration")
    public String toregistration(){
        logger.info("定向注册页");
        return "registration";
    }

    @RequestMapping("registrationOfSchool")
    public String toRegistrationOfSchool(){
        logger.info("定向校方申请页");
        return "registrationOfSchool";
    }

    @RequestMapping("registrationOfCompany")
    public String toRegistrationOfCompany(){
        logger.info("定向企业申请页");
        return "registrationOfCompany";
    }

    @RequestMapping("testPaper")
    public String toTestPaper(){
        logger.info("定向测试页");
        return "testQuestion/testPaper";
    }

    @RequestMapping("login")
    public String tologin(){
        logger.info("定向登陆页");
        return "login";
    }

    @RequestMapping("home")
    public String home(){
        logger.info("定向主页");
        return "home";
    }

    @RequestMapping("logout")
    public String logout(){
        logger.info("退出系统");
        Subject subject = SecurityUtils.getSubject();
        subject.logout(); // shiro底层删除session的会话信息
        return "redirect:login";
    }

    @RequestMapping("index")
    public String toIndex(){
        logger.info("定向主页");
        return "index";
    }

    @RequestMapping("case")
    public String toCase(){
        logger.info("定向成功案例界面");
        return "case";
    }

    @RequestMapping("myError")
    public String toError(){
        logger.info("定向拒绝访问界面");
        return "404";
    }

    @RequestMapping("testSucceed")
    public String toTestSucceed(){
        logger.info("定向测试成功界面");
        return "testQuestion/testSucceed";
    }

    @RequestMapping("testResult")
    public String toTestResult(){
        logger.info("定向测试结果界面");
        return "testQuestion/testResultById";
    }

    @RequestMapping("financeInfo")
    public String toFinanceInfo(){
        logger.info("定向融资信息界面");
        return "financeInfo";
    }

    @RequestMapping("projectInfo")
    public String toProjectInfo(){
        logger.info("定向项目信息界面");
        return "projectInfo";
    }

    /**
     * 测试提交[Branch--->mymymy]
     */

}
