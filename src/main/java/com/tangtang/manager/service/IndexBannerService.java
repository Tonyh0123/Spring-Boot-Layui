package com.tangtang.manager.service;

import com.tangtang.manager.pojo.BaseIndexBanner;
import com.tangtang.manager.pojo.BasePolicy;
import com.tangtang.manager.response.PageDataResult;

import java.util.List;
import java.util.Map;

public interface IndexBannerService {

    PageDataResult getBannerList(BaseIndexBanner baseIndexBanner, Integer pageNum, Integer pageSize);

    Map<String,Object> addBanner(BaseIndexBanner baseIndexBanner);

    List<BaseIndexBanner> getBannerList();

    Map<String, Object> del(long id);
}
