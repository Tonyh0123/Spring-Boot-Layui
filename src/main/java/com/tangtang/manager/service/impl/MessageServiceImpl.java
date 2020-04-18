package com.tangtang.manager.service.impl;

import com.tangtang.manager.dao.BaseMessageMapper;
import com.tangtang.manager.pojo.BaseMessage;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseMessageMapper messageMapper;


    @Override
    public PageDataResult getMessageByUserName(String message_owner, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        pageNum = (pageNum-1)*pageSize;
        //先获得数据总数，用于分页，pageSize设置为100000是尽可能往大的数去设置
        List<BaseMessage> nums = messageMapper.getMessageByUserName(message_owner,0,100000);
        //然后获取每页需要显示的条数
        List<BaseMessage> baseMessages = messageMapper.getMessageByUserName(message_owner,pageNum,pageSize);
        if(baseMessages.size() != 0){
            pageDataResult.setList(baseMessages);
            pageDataResult.setTotals(nums.size());
        }
        return pageDataResult;
    }

    @Override
    public Map<String, Object> addMessage(BaseMessage baseMessage) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = messageMapper.addMessage(baseMessage);
            if(!result){
                data.put("code",0);
                data.put("msg","留言失败，请联系管理！");
                logger.error("新增留言，结果=新增失败，数据插入异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","留言成功！");
            logger.info("新增留言，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增留言异常！", e);
            return data;
        }
        return data;
    }

}
