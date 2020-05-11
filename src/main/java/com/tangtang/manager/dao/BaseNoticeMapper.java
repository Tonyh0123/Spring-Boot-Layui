package com.tangtang.manager.dao;

import com.tangtang.manager.dto.NoticeSerachDTO;
import com.tangtang.manager.pojo.BaseNotice;
import com.tangtang.manager.pojo.BaseSuccessfulCase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface BaseNoticeMapper extends MyMapper<BaseNotice> {

    boolean addNotice(BaseNotice baseNotice);

    List<BaseNotice> getNoticeListForManage(BaseNotice notice, Integer pageNum, Integer pageSize);

    List<BaseNotice> getNoticeList(@Param("serachDTO")NoticeSerachDTO serachDTO);

    List<BaseNotice> getNoticeList(@Param("serachDTO")NoticeSerachDTO serachDTO, Integer pageNum, Integer pageSize);
}
