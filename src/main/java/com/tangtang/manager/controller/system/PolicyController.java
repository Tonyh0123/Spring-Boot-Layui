package com.tangtang.manager.controller.system;

import com.tangtang.manager.dto.PolicySerachDTO;
import com.tangtang.manager.pojo.BaseNotice;
import com.tangtang.manager.pojo.BasePolicy;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.NoticeService;
import com.tangtang.manager.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: PolicyController
 * @Description: 政策管理
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/03/25 11：57
 */
@Controller
@RequestMapping("policy")
public class PolicyController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PolicyService policyService;


    /**
     *
     * 功能描述: 跳到政策列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/25 11：58
     */
    @RequestMapping("/policyManage")
    public String policyManage() {
        return "/policy/policyManage";
    }


    /**
     *
     * 功能描述: 分页查询政策管理列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/25 11：59
     */
    @RequestMapping(value = "/getPolicyList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getPolicyList(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize, BasePolicy basePolicy) {

        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取政策列表
            pdr = policyService.getPolicyList(basePolicy, pageNum ,pageSize);
            logger.info("政策列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("政策列表查询异常！", e);
        }
        return pdr;
    }

    @RequestMapping(value = "/getAllPolicies", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getAllPolicies(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize, BasePolicy basePolicy) {

        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 20;
            }
            // 获取政策列表
            pdr = policyService.getPolicyList(basePolicy, pageNum ,pageSize);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("政策列表查询异常！", e);
        }
        return pdr;
    }

    /**
     * 新增政策
     * @param basePolicy
     * @return
     */
    @RequestMapping(value = "/addPolicy", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addPolicy(BasePolicy basePolicy) {
        logger.info("新增政策 ------> basePolicy:" + basePolicy);
        Map<String,Object> data = new HashMap();
        data = policyService.addPolicy(basePolicy);
        return data;
    }


    @RequestMapping(value = "/policyShowData", method = RequestMethod.POST)
    public @ResponseBody List<BasePolicy> getNoticeShowData(PolicySerachDTO serachDTO){
        logger.info("获取政策展示数据");
        List<BasePolicy> basePolicies = policyService.getPolicyList(serachDTO);
        return basePolicies;
    }

    /**
     * 删除政策
     * @Author tangtang
     * @param id
     * @return
     */
    @PostMapping("del")
    @ResponseBody
    public Map<String, Object> del(@RequestParam("id") Long id) {
        logger.info("删除政策！id:" + id);
        Map<String, Object> data = new HashMap<>();
        data = policyService.del(id);
        return data;
    }


}
