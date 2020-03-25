package com.tangtang.manager.dao;

import com.tangtang.manager.pojo.BaseNotice;
import com.tangtang.manager.pojo.BasePolicy;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface BasePolicyMapper extends MyMapper<BasePolicy> {

    boolean addPolicy(BasePolicy policy);

    List<BasePolicy> getPolicyList(BasePolicy policy);

    List<BasePolicy> getPolicyList();
}
