package com.tangtang.manager.controller.system;

import com.tangtang.manager.dto.*;
import com.tangtang.manager.pojo.BaseAdminUser;
import com.tangtang.manager.pojo.BaseCompany;
import com.tangtang.manager.pojo.BaseProjectDeclaration;
import com.tangtang.manager.pojo.BaseStudent;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.AdminUserService;
import com.tangtang.manager.service.ApplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: UserController
 * @Description: 系统用户管理
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/03/09 15:17
 */
@Controller
@RequestMapping("apply")
public class ApplyController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private ApplyService applyService;


    /**
     *
     * 功能描述: 跳到学校申请列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/09 13:50
     */
    @RequestMapping("/applyManage")
    public String applyManage() {
        return "/apply/schoolApply";
    }

    /**
     *
     * 功能描述: 跳到学生申请列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/02 13:55
     */
    @RequestMapping("/studentApplyManage")
    public String studentApplyManage() {
        return "/apply/studentApply";
    }

    /**
     *
     * 功能描述: 跳到企业申请列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/22 13:55
     */
    @RequestMapping("/companyApplyManage")
    public String companyApplyManage() {
        return "/apply/companyApply";
    }

    /**
     *
     * 功能描述: 分页查询申请列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/09 11:10
     */
    @RequestMapping(value = "/getSchoolApplyList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getUserList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,/*@Valid PageRequest page,*/ SchoolApplyDTO schoolApplyDTO) {
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
            pdr = applyService.getSchoolApplyList(schoolApplyDTO, pageNum ,pageSize);
            logger.info("高校申请列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("高校申请列表查询异常！", e);
        }
        return pdr;
    }


    /**
     *
     * 功能描述: 分页查询学生审核信息
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/02 11:10
     */
    @RequestMapping(value = "/getStudentApplyList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getStudentList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize, BaseStudent student) {
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取学生审核信息列表
            pdr = applyService.getStudentApplyList(student, pageNum ,pageSize);
            logger.info("学生审核信息列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("学生审核信息列表查询异常！", e);
        }
        return pdr;
    }


    /**
     *
     * 功能描述: 分页查询企业申请信息
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/22 11:10
     */
    @RequestMapping(value = "/getCompanyApplyList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getCompanyApplyList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize, BaseCompany company) {
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            pdr = applyService.getCompanyApplyList(company, pageNum ,pageSize);
            logger.info("企业审核信息列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("企业审核信息列表查询异常！", e);
        }
        return pdr;
    }


    /**
     * 注册用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/regUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> regUser(StudentRegistrationDTO user) {
        logger.info("为高校创建账号[注册用户]！user:" + user);
        Map<String,Object> data = new HashMap();
        data = adminUserService.regUser(user);
        data.put("url","/login");
        return data;
    }


    /**
     * 院校申请审核
     * @param user
     * @return
     */
    @RequestMapping(value = "/regSchoolUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> regSchoolUser(SchoolRegistrationDTO user) {
        logger.info("院校申请使用 ------> user:" + user);
        Map<String,Object> data = new HashMap();
        data = applyService.confirmSchoolsApply(user);
        return data;
    }

    /**
     * 企业申请审核
     * @param companyRegistrationDTO
     * @return
     */
    @RequestMapping(value = "/judgeCompanyUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> judgeCompanyUser(CompanyRegistrationDTO companyRegistrationDTO) {
        logger.info("企业申请审核");
        Map<String,Object> data = new HashMap();
        data = applyService.confirmCompanyApply(companyRegistrationDTO);
        return data;
    }


    /**
     * 学生认证申请审核
     * @param student
     * @return
     */
    @RequestMapping(value = "/verifyStudentStatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> verifyStudentStatus(BaseStudent student) {
        logger.info("学生认证申请审核");
        Map<String,Object> data = new HashMap();
        data = applyService.verifyStudentApply(student);
        return data;
    }

    /**
     *
     * 功能描述: 删除/恢复 用户
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/10 11:59
     */
    @RequestMapping(value = "/updateUserStatus", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateUserStatus(@RequestParam("id") Integer id,@RequestParam("status") Integer status) {
        logger.info("删除/恢复用户！id:" + id+" status:"+status);
        Map<String, Object> data = new HashMap<>();
        if(status == 0){
            //删除用户
            data = adminUserService.delUser(id,status);
        }else{
            //恢复用户
            data = adminUserService.recoverUser(id,status);
        }
        return data;
    }

    @RequestMapping(value = "/userIsExist", method = RequestMethod.POST, consumes="application/json")
    @ResponseBody
    public Boolean userIsExist(@RequestBody BaseAdminUser user) {
        logger.info("注册用户[检测用户名是否存在]！username:" + user.getSysUserName());
        String username = user.getSysUserName();
        System.out.println(username);
        //根据用户名查询管理员(包括status为0的   以防恢复引起bug)
        BaseAdminUser admin1=adminUserService.findByUserName(username);
        if(admin1==null)
        {
            System.out.println(111);
            //返回true则为没有该用户名可以被注册
            return true;
        }else {
            System.out.println(222);
            return false;
        }
    }


}
