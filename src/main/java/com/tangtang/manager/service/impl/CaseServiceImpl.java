package com.tangtang.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangtang.manager.dao.BaseAdminUserMapper;
import com.tangtang.manager.dao.BaseRegistrationMapper;
import com.tangtang.manager.dao.BaseSuccessfulCaseMapper;
import com.tangtang.manager.dto.SchoolApplyDTO;
import com.tangtang.manager.pojo.BaseSuccessfulCase;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.CaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CaseServiceImpl implements CaseService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseSuccessfulCaseMapper successfulCaseMapper;

    @Override
    public Map<String, Object> addCase(BaseSuccessfulCase successfulCase) {
        Map<String,Object> data = new HashMap();
        try {
            successfulCase.setCaseIdentifier(UUID.randomUUID().toString().replace("-",""));
            boolean result = successfulCaseMapper.addCase(successfulCase);
            if(!result){
                data.put("code",0);
                data.put("msg","操作失败，请联系管理！");
                logger.error("新增成功案例，结果=新增失败，数据插入异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增成功！");
            logger.info("新增成功案例，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增成功案例异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public PageDataResult getCaseList(BaseSuccessfulCase successfulCase, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<BaseSuccessfulCase> baseSuccessfulCases = successfulCaseMapper.getCaseList(successfulCase);
        PageHelper.startPage(pageNum, pageSize);
        if(baseSuccessfulCases.size() != 0){
            PageInfo<BaseSuccessfulCase> pageInfo = new PageInfo<>(baseSuccessfulCases);
            pageDataResult.setList(baseSuccessfulCases);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }

    @Override
    public List<BaseSuccessfulCase> getCaseShowData() {
        return successfulCaseMapper.getCaseList();
    }
}
