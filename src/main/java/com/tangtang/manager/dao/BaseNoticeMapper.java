package com.tangtang.manager.dao;

import com.tangtang.manager.pojo.BaseNotice;
import com.tangtang.manager.pojo.BaseSuccessfulCase;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface BaseNoticeMapper extends MyMapper<BaseNotice> {

    boolean addNotice(BaseNotice baseNotice);

    List<BaseNotice> getNoticeList(BaseNotice notice);

    List<BaseNotice> getNoticeList();
}
