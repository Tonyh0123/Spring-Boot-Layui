package com.tangtang.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangtang.manager.dao.BasePolicyMapper;
import com.tangtang.manager.dto.PolicySerachDTO;
import com.tangtang.manager.pojo.BaseNotice;
import com.tangtang.manager.pojo.BasePolicy;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PolicyServiceImpl implements PolicyService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BasePolicyMapper policyMapper;


    @Override
    public PageDataResult getPolicyList(BasePolicy policy, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        pageNum = (pageNum-1)*pageSize;
        List<BasePolicy> nums = policyMapper.getPolicyListForManage(policy,0,100000);
        List<BasePolicy> basePolicies = policyMapper.getPolicyListForManage(policy,pageNum,pageSize);
        if(basePolicies.size() != 0){
            pageDataResult.setList(basePolicies);
            pageDataResult.setTotals(nums.size());
        }
        return pageDataResult;
    }

    @Override
    public Map<String, Object> addPolicy(BasePolicy basePolicy) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = policyMapper.addPolicy(basePolicy);
            if(!result){
                data.put("code",0);
                data.put("msg","操作失败，请联系管理！");
                logger.error("新增政策，结果=新增失败，数据插入异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增成功！");
            logger.info("新增政策，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增政策异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public List<BasePolicy> getPolicyList(PolicySerachDTO serachDTO) {
        return policyMapper.getPolicyList(serachDTO);
    }

    @Override
    public Map<String, Object> del(long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = policyMapper.deleteByPrimaryKey(id);
            if(result == 0){
                data.put("code",0);
                data.put("msg","删除失败，id:" + id);
                logger.error("删除失败");
                return data;
            }
            data.put("code",1);
            data.put("msg","政策删除成功，id:" + id);
            logger.info("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除政策异常！", e);
        }
        return data;
    }

}
