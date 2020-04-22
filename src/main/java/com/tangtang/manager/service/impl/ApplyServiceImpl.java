package com.tangtang.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangtang.manager.common.utils.DateUtils;
import com.tangtang.manager.common.utils.DigestUtils;
import com.tangtang.manager.common.utils.PinyinUtils;
import com.tangtang.manager.dao.BaseApplyMapper;
import com.tangtang.manager.dao.BaseRegistrationMapper;
import com.tangtang.manager.dto.CompanyRegistrationDTO;
import com.tangtang.manager.dto.SchoolApplyDTO;
import com.tangtang.manager.dto.SchoolRegistrationDTO;
import com.tangtang.manager.pojo.BaseCompany;
import com.tangtang.manager.pojo.BaseStudent;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.ApplyService;
import com.tangtang.manager.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ApplyServiceImpl implements ApplyService {
    @Autowired
    private BaseApplyMapper baseApplyMapper;
    @Autowired
    private BaseRegistrationMapper baseRegistrationMapper;

    @Autowired
    private MailService mailService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private PinyinUtils pinyinUtils;

    @Override
    public PageDataResult getSchoolApplyList(SchoolApplyDTO schoolApplyDTO, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<SchoolApplyDTO> schoolApplyDTOS = baseApplyMapper.getSchoolApplyList(schoolApplyDTO);

        PageHelper.startPage(pageNum, pageSize);

        if(schoolApplyDTOS.size() != 0){
            PageInfo<SchoolApplyDTO> pageInfo = new PageInfo<>(schoolApplyDTOS);
            pageDataResult.setList(schoolApplyDTOS);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }

    @Override
    public PageDataResult getStudentApplyList(BaseStudent student, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<BaseStudent> students = baseApplyMapper.getStudentApplyList(student);
        PageHelper.startPage(pageNum, pageSize);
        if(students.size() != 0){
            PageInfo<BaseStudent> pageInfo = new PageInfo<>(students);
            pageDataResult.setList(students);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }
        return pageDataResult;
    }

    @Override
    public PageDataResult getCompanyApplyList(BaseCompany company, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<BaseCompany> companies = baseApplyMapper.getCompanyApplyList(company);
        PageHelper.startPage(pageNum, pageSize);
        if(companies.size() != 0){
            PageInfo<BaseCompany> pageInfo = new PageInfo<>(companies);
            pageDataResult.setList(companies);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }
        return pageDataResult;
    }

    @Override
    public Map<String, Object> confirmSchoolsApply(SchoolRegistrationDTO schoolRegistrationDTO) {
        Map<String,Object> data = new HashMap();
        try {

            String usernameZW = schoolRegistrationDTO.getSysUserName();
            String username = PinyinUtils.getPinYinHeadChar(usernameZW);
            schoolRegistrationDTO.setSysUserName(username);
            String firstPassword = "123456";
            if(schoolRegistrationDTO.getSysUserPwd() == null || schoolRegistrationDTO.getSysUserPwd() == ""){
                String password = DigestUtils.Md5(username,firstPassword);
                schoolRegistrationDTO.setSysUserPwd(password);
            }else{
                String password = DigestUtils.Md5(username, schoolRegistrationDTO.getSysUserPwd());
                schoolRegistrationDTO.setSysUserPwd(password);
            }
            schoolRegistrationDTO.setRegTime(DateUtils.getCurrentDate());
            schoolRegistrationDTO.setUserStatus(1);
            schoolRegistrationDTO.setRoleId(3); //  '3'===高校
            boolean result1 = baseRegistrationMapper.confirmSchoolsApply(schoolRegistrationDTO);
            int iddd = schoolRegistrationDTO.getId();
            System.out.println("看过来看过来   iddd---->"+iddd);
            int result2 = baseRegistrationMapper.updateSchoolInfo(schoolRegistrationDTO);
            if(!result1 && result2<1){
                data.put("code",0);
                data.put("msg","新增失败！");
                logger.error("用户[新增]，结果=新增失败！");
                return data;
            }
            //开始发送邮件
            mailService.sendSimpleMail(schoolRegistrationDTO.getSchoolManagerEmail(),"账户开通成功",
                    "用户名："+schoolRegistrationDTO.getSysUserName()+"密码："+firstPassword);
            data.put("code",1);
            data.put("msg","新增成功！");
            logger.info("用户[新增]，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("用户[新增]异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public Map<String, Object> confirmCompanyApply(CompanyRegistrationDTO companyRegistrationDTO) {
        Map<String,Object> data = new HashMap();
        try {
            if(companyRegistrationDTO.getApplyFlag().equals("yes")){
                String usernameZW = companyRegistrationDTO.getSysUserName();
                String username = PinyinUtils.getPinYinHeadChar(usernameZW);
                companyRegistrationDTO.setSysUserName(username);
                String firstPassword = "123456";
                if(companyRegistrationDTO.getSysUserPwd() == null || companyRegistrationDTO.getSysUserPwd() == ""){
                    String password = DigestUtils.Md5(username,firstPassword);
                    companyRegistrationDTO.setSysUserPwd(password);
                }else{
                    firstPassword = companyRegistrationDTO.getSysUserPwd();
                    String password = DigestUtils.Md5(username, companyRegistrationDTO.getSysUserPwd());
                    companyRegistrationDTO.setSysUserPwd(password);

                }
                companyRegistrationDTO.setRegTime(DateUtils.getCurrentDate());
                companyRegistrationDTO.setUserStatus(1);
                companyRegistrationDTO.setRoleId(4); //  '4'===企业
                companyRegistrationDTO.setApply_status("1");
                boolean result1 = baseRegistrationMapper.confirmCompanyApply(companyRegistrationDTO);
                int iddd = companyRegistrationDTO.getId();
                System.out.println("看过来看过来   iddd---->"+iddd);
                int result2 = baseRegistrationMapper.updateCompanyInfo(companyRegistrationDTO);
                if(!result1 && result2<1){
                    data.put("code",0);
                    data.put("msg","新增失败！");
                    logger.error("用户[新增]，结果=新增失败！");
                    return data;
                }
                //开始发送邮件
                mailService.sendSimpleMail(companyRegistrationDTO.getCompany_contacts_email(),"账户开通成功",
                        "用户名："+companyRegistrationDTO.getSysUserName()+"密码："+firstPassword);
                data.put("code",1);
                data.put("msg","新增成功！");
                logger.info("用户[新增]，结果=新增成功！");
            }else {
                companyRegistrationDTO.setApply_status("-1");
                int result3 = baseRegistrationMapper.updateCompanyInfo(companyRegistrationDTO);
                if(result3<1){
                    data.put("code",0);
                    data.put("msg","审核失败！");
                    logger.error("企业信息更新，结果=失败！");
                    return data;
                }
                mailService.sendSimpleMail(companyRegistrationDTO.getCompany_contacts_email(),"审核未通过",
                        "审核意见："+companyRegistrationDTO.getApply_opinion());
                data.put("code",1);
                data.put("msg","审核成功！");
                logger.info("企业信息更新，结果=成功！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("企业申请审核异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public Map<String, Object> verifyStudentApply(BaseStudent student) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = baseApplyMapper.verifyStudentApply(student);
            if(!result){
                data.put("code",0);
                data.put("msg","学生信息更新失败！");
                logger.error("同意/不同意学生申请，结果=更新失败！");
                return data;
            }
            data.put("code",1);
            data.put("msg","学生信息更新成功！");
            logger.info("同意/不同意学生申请，结果=更新成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("学生申请审核异常！", e);
            return data;
        }
        return data;
    }
}
