package com.tangtang.manager.dao;

import com.tangtang.manager.pojo.BaseFinance;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;


@Repository
public interface BaseFinanceMapper extends MyMapper<BaseFinance> {
    boolean addProjectFinance(BaseFinance finance);

    List<BaseFinance> getProjectFinanceByProjectId(BaseFinance finance);

}
