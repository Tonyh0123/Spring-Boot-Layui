package com.tangtang.manager.service;

import com.tangtang.manager.pojo.BaseNotice;
import com.tangtang.manager.pojo.BaseSuccessfulCase;
import com.tangtang.manager.response.PageDataResult;

import java.util.List;
import java.util.Map;

public interface NoticeService {

    PageDataResult getNoticeList(BaseNotice notice, Integer pageNum, Integer pageSize);

    Map<String,Object> addNotice(BaseNotice baseNotice);

    List<BaseNotice> getNoticeList();
}
