package com.tangtang.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangtang.manager.common.utils.DateUtils;
import com.tangtang.manager.dao.BaseProjectDeclarationMapper;
import com.tangtang.manager.dao.BaseTestQuestionMapper;
import com.tangtang.manager.pojo.BaseProjectDeclaration;
import com.tangtang.manager.pojo.BaseTestQuestion;
import com.tangtang.manager.pojo.BaseTestResult;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.ProjectDeclarationService;
import com.tangtang.manager.service.TestQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectDeclarationServiceImpl implements ProjectDeclarationService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseProjectDeclarationMapper projectDeclarationMapper;

    @Override
    public Map<String,Object> addProjectEstablishApply(BaseProjectDeclaration projectDeclaration) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = projectDeclarationMapper.addProjectEstablishApply(projectDeclaration);
            if(!result){
                data.put("code",0);
                data.put("msg","操作失败，请联系管理！");
                logger.error("新增项目申报，结果=新增失败，数据插入异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增成功！");
            logger.info("新增项目申报，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增项目申报异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public String getSchoolNameByUserId(String userId) {
        String schoolName = projectDeclarationMapper.getSchoolNameByUserId(userId);
        return schoolName;
    }

    @Override
    public Map<String, Object> updateProjectDetail(BaseProjectDeclaration projectDeclaration) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = projectDeclarationMapper.updateProjectDetail(projectDeclaration);
            if(!result){
                data.put("code",0);
                data.put("msg","操作失败，请联系管理！");
                logger.error("审核通过并安排立项答辩时间，结果=失败，数据更新异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","更新成功！");
            logger.info("审核通过并安排立项答辩时间，结果=成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("审核通过并安排立项答辩时间异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public Map<String, Object> agreeTheLXApply(BaseProjectDeclaration projectDeclaration) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = projectDeclarationMapper.agreeTheLXApply(projectDeclaration);
            if(!result){
                data.put("code",0);
                data.put("msg","操作失败，请联系管理！");
                logger.error("学校予以立项，结果=失败，数据更新异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","学校予以立项，更新成功！");
            logger.info("学校予以立项，结果=成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("学校予以立项异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public Map<String, Object> JDBGApply(BaseProjectDeclaration projectDeclaration) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = projectDeclarationMapper.JDBGApply(projectDeclaration);
            if(!result){
                data.put("code",0);
                data.put("msg","阶段变更申请操作失败，请联系管理！");
                logger.error("阶段变更申请，结果=失败，数据更新异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","阶段变更申请，操作成功！");
            logger.info("阶段变更申请，结果=成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("阶段变更申请异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public Map<String, Object> setTimeOfDB(BaseProjectDeclaration projectDeclaration) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = projectDeclarationMapper.setTimeOfDB(projectDeclaration);
            if(!result){
                data.put("code",0);
                data.put("msg","设置阶段变更答辩时间操作失败，请联系管理！");
                logger.error("设置阶段变更答辩时间，结果=失败，数据更新异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","设置阶段变更答辩时间，操作成功！");
            logger.info("设置阶段变更答辩时间，结果=成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("设置阶段变更答辩时间异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public Map<String, Object> setCurrentJD(BaseProjectDeclaration projectDeclaration) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = projectDeclarationMapper.setCurrentJD(projectDeclaration);
            if(!result){
                data.put("code",0);
                data.put("msg","设置项目当前阶段操作失败，请联系管理！");
                logger.error("设置项目当前阶段，结果=失败，数据更新异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","设置项目当前阶段，操作成功！");
            logger.info("设置项目当前阶段，结果=成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("设置项目当前阶段异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public PageDataResult getLXProjectListForSchool(BaseProjectDeclaration projectDeclaration, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<BaseProjectDeclaration> projectDeclarations = projectDeclarationMapper.getLXProjectListForSchool(projectDeclaration);
        PageHelper.startPage(pageNum, pageSize);
        if(projectDeclarations.size() != 0){
            PageInfo<BaseProjectDeclaration> pageInfo = new PageInfo<>(projectDeclarations);
            pageDataResult.setList(projectDeclarations);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }

    @Override
    public Map<String, Object> recommendOrNot(BaseProjectDeclaration projectDeclaration) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = projectDeclarationMapper.recommendOrNot(projectDeclaration);
            if(!result){
                data.put("code",0);
                data.put("msg","推荐项目至企业，操作失败，请联系管理！");
                logger.error("推荐项目至企业，结果=失败，数据更新异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","推荐项目至企业，操作成功！");
            logger.info("推荐项目至企业，结果=成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("推荐项目至企业异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public String checkCurrentJD(BaseProjectDeclaration projectDeclaration) {
        return null;
    }

    @Override
    public PageDataResult getProjectEstablishListForSchool(BaseProjectDeclaration projectDeclaration, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<BaseProjectDeclaration> projectDeclarations = projectDeclarationMapper.getProjectEstablishListForSchool(projectDeclaration);
        PageHelper.startPage(pageNum, pageSize);
        if(projectDeclarations.size() != 0){
            PageInfo<BaseProjectDeclaration> pageInfo = new PageInfo<>(projectDeclarations);
            pageDataResult.setList(projectDeclarations);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }

    @Override
    public PageDataResult getPersonalProjectEstablishList(BaseProjectDeclaration projectDeclaration, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<BaseProjectDeclaration> projectDeclarations = projectDeclarationMapper.getPersonalProjectEstablishList(projectDeclaration);
        PageHelper.startPage(pageNum, pageSize);
        if(projectDeclarations.size() != 0){
            PageInfo<BaseProjectDeclaration> pageInfo = new PageInfo<>(projectDeclarations);
            pageDataResult.setList(projectDeclarations);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }



    @Override
    public Map<String, Object> del(long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            // 删除项目申报
            int result = projectDeclarationMapper.deleteByPrimaryKey(id);
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
            logger.error("删除项目申报异常！", e);
        }
        return data;
    }

}
