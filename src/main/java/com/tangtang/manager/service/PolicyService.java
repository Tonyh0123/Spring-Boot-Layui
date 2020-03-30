package com.tangtang.manager.service;

import com.tangtang.manager.dto.PolicySerachDTO;
import com.tangtang.manager.pojo.BasePolicy;
import com.tangtang.manager.response.PageDataResult;

import java.util.List;
import java.util.Map;

public interface PolicyService {

    PageDataResult getPolicyList(BasePolicy policy, Integer pageNum, Integer pageSize);

    Map<String,Object> addPolicy(BasePolicy basePolicy);

    List<BasePolicy> getPolicyList(PolicySerachDTO serachDTO);

    Map<String, Object> del(long id);
}
