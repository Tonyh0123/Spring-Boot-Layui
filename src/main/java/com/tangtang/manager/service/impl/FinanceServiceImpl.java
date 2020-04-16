package com.tangtang.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangtang.manager.dao.BaseFinanceMapper;
import com.tangtang.manager.dao.BaseProjectDeclarationMapper;
import com.tangtang.manager.pojo.BaseFinance;
import com.tangtang.manager.pojo.BaseProjectDeclaration;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.FinanceService;
import com.tangtang.manager.service.ProjectDeclarationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FinanceServiceImpl implements FinanceService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseFinanceMapper financeMapper;

    @Override
    public Map<String,Object> addProjectFinance(BaseFinance finance) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = financeMapper.addProjectFinance(finance);
            if(!result){
                data.put("code",0);
                data.put("msg","操作失败，请联系管理！");
                logger.error("新增项目融资，结果=新增失败，数据插入异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增成功！");
            logger.info("新增项目融资，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增项目融资异常！", e);
            return data;
        }
        return data;
    }


    @Override
    public PageDataResult getProjectFinanceByProjectId(BaseFinance finance, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<BaseFinance> financeByProjectId = financeMapper.getProjectFinanceByProjectId(finance);
        PageHelper.startPage(pageNum, pageSize);
        if(financeByProjectId.size() != 0){
            PageInfo<BaseFinance> pageInfo = new PageInfo<>(financeByProjectId);
            pageDataResult.setList(financeByProjectId);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }
        return pageDataResult;
    }


    @Override
    public Map<String, Object> del(long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = financeMapper.deleteByPrimaryKey(id);
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
            logger.error("删除项目融资异常！", e);
        }
        return data;
    }

}
