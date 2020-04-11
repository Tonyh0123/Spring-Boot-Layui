package com.tangtang.manager.controller.system;

import com.tangtang.manager.common.utils.DateUtils;
import com.tangtang.manager.dto.TestTargetResultDTO;
import com.tangtang.manager.dto.TestTargetScoreDTO;
import com.tangtang.manager.pojo.BaseProjectDeclaration;
import com.tangtang.manager.pojo.BaseStudent;
import com.tangtang.manager.pojo.BaseTestQuestion;
import com.tangtang.manager.pojo.BaseTestResult;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.ProjectDeclarationService;
import com.tangtang.manager.service.TestQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @Title: ProjectDeclarationController
 * @Description: 项目申报管理
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/04/08 11：57
 */
@Controller
@RequestMapping("declaration")
public class ProjectDeclarationController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProjectDeclarationService projectDeclarationService;


    /**
     *
     * 功能描述: 跳到校方项目申报管理列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/08 11：59
     */
    @RequestMapping("/projectDeclarationManageForSchool")
    public String projectDeclarationManageForSchool() {
        return "/projectDeclarationManage/projectDeclarationListForSchoolManage";
    }

    /**
     *
     * 功能描述: 跳到学生个人项目申报管理列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/08 11：59
     */
    @RequestMapping("/projectDeclarationManageForStudent")
    public String projectDeclarationManageForStudent() {
        return "/projectDeclarationManage/projectDeclarationListForStudentManage";
    }


    /**
     *
     * 功能描述: 分页查询校方项目申报管理列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/08 12：05
     */
    @RequestMapping(value = "/getProjectEstablishListForSchool", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getProjectEstablishListForSchool(@RequestParam("pageNum") Integer pageNum,
                                                           @RequestParam("pageSize") Integer pageSize, BaseProjectDeclaration projectDeclaration) {

        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取列表
            pdr = projectDeclarationService.getProjectEstablishListForSchool(projectDeclaration, pageNum ,pageSize);
            logger.info("校方项目申报管理列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("校方项目申报管理列表查询异常！", e);
        }
        return pdr;
    }

    /**
     *
     * 功能描述: 分页查询个人项目申报管理列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/08 12：09
     */
    @RequestMapping(value = "/getPersonalProjectEstablishList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getPersonalProjectEstablishList(@RequestParam("pageNum") Integer pageNum,
                                                           @RequestParam("pageSize") Integer pageSize,
                                                          BaseProjectDeclaration projectDeclaration, @RequestParam("userId") String userId) {

        projectDeclaration.setProjectOwnerId(userId);
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取列表
            pdr = projectDeclarationService.getPersonalProjectEstablishList(projectDeclaration, pageNum ,pageSize);
            logger.info("学生个人项目申报管理列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("学生个人项目申报管理列表查询异常！", e);
        }
        return pdr;
    }


    /**
     * 新增项目申报
     * @param projectDeclaration
     * @return
     */
    @RequestMapping(value = "/addProjectEstablishApply", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addProjectEstablishApply(BaseProjectDeclaration projectDeclaration) {
        String proId = "DC"+DateUtils.getNowDateString().replace("-","");
        Random random = new Random();
        for(int i=0;i<4;i++){
            int ran = random.nextInt(10);
            proId += ran;
        }
        projectDeclaration.setProjectId(proId.toString());
        Map<String,Object> data = new HashMap();
        logger.info("新增项目申报");
        data = projectDeclarationService.addProjectEstablishApply(projectDeclaration);
        return data;
    }

    /**
     *
     * 功能描述: 更新项目申报信息
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/08 12：19
     */
    @RequestMapping(value = "/updateProjectDetail", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateProjectDetail(BaseProjectDeclaration projectDeclaration) {
        logger.info("更新项目申报信息");
        Map<String, Object> data = projectDeclarationService.updateProjectDetail(projectDeclaration);
        return data;
    }


    /**
     * 删除项目申报
     * @Author tangtang
     * @param id
     * @return
     */
    @PostMapping("del")
    @ResponseBody
    public Map<String, Object> del(@RequestParam("id") Long id) {
        logger.info("删除项目申报！id:" + id);
        Map<String, Object> data = new HashMap<>();
        data = projectDeclarationService.del(id);
        return data;
    }



}
