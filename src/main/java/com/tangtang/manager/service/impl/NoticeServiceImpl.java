package com.tangtang.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangtang.manager.dao.BaseNoticeMapper;
import com.tangtang.manager.dao.BaseSuccessfulCaseMapper;
import com.tangtang.manager.pojo.BaseNotice;
import com.tangtang.manager.pojo.BaseSuccessfulCase;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.CaseService;
import com.tangtang.manager.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class NoticeServiceImpl implements NoticeService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseNoticeMapper noticeMapper;


    @Override
    public PageDataResult getNoticeList(BaseNotice notice, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<BaseNotice> baseNotices = noticeMapper.getNoticeList(notice);
        PageHelper.startPage(pageNum, pageSize);
        if(baseNotices.size() != 0){
            PageInfo<BaseNotice> pageInfo = new PageInfo<>(baseNotices);
            pageDataResult.setList(baseNotices);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }

        return pageDataResult;
    }

    @Override
    public Map<String, Object> addNotice(BaseNotice baseNotice) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = noticeMapper.addNotice(baseNotice);
            if(!result){
                data.put("code",0);
                data.put("msg","操作失败，请联系管理！");
                logger.error("新增公告，结果=新增失败，数据插入异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增成功！");
            logger.info("新增公告，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增公告异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public List<BaseNotice> getNoticeList() {
        return noticeMapper.getNoticeList();
    }

}
