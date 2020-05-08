package com.tangtang.manager.controller.system;

import com.tangtang.manager.service.AdminUserService;
import com.tangtang.manager.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("verifyCode")
public class VerifyCodeController {

    @Autowired
    private AdminUserService adminUserService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MailService mailService;

    @RequestMapping(value = "/checkVerifyCode", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> checkVerifyCode(String verifyCode, String email){
        Map<String,Object> data = new HashMap();
        Integer studentEmail = adminUserService.checkStudentEmail(email);
        Integer schoolEmail = adminUserService.checkSchoolEmail(email);
        Integer companyEmail = adminUserService.checkCompanyEmail(email);
        if(verifyCode==null && email==null){
            data.put("code",0);
            data.put("msg","验证码发送异常！");
            return data;
        }
        if(studentEmail==0 && schoolEmail==0 && companyEmail==0){
            try {
                mailService.sendSimpleMail(email,"验-证-码", "验-证-码："+verifyCode);
            }catch (Exception e){
                data.put("msg","验证码发送异常！");
                logger.error("邮件发送异常");
            }
            data.put("code",1);
            data.put("msg","验证码发送成功！");
        }else {
            data.put("code",0);
            data.put("msg","邮箱已被注册！");
        }
        return data;
    }
}
