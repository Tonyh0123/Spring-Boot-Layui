package com.tangtang.manager.dao;

import com.tangtang.manager.pojo.BaseIndexBanner;
import com.tangtang.manager.pojo.BasePolicy;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface BaseIndexBannerMapper extends MyMapper<BaseIndexBanner> {

    boolean addBanner(BaseIndexBanner baseIndexBanner);

    List<BaseIndexBanner> getBannerList(BaseIndexBanner baseIndexBanner);

    List<BaseIndexBanner> getBannerList();
}
