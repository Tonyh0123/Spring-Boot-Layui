package com.tangtang.manager.service.impl;

import com.tangtang.manager.dao.BaseMessageMapper;
import com.tangtang.manager.dao.BaseReplyMapper;
import com.tangtang.manager.pojo.BaseMessage;
import com.tangtang.manager.pojo.BaseReply;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.MessageService;
import com.tangtang.manager.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReplyServiceImpl implements ReplyService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseReplyMapper replyMapper;


    @Override
    public PageDataResult getMessageReply(String message_id, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        pageNum = (pageNum-1)*pageSize;
        //先获得数据总数，用于分页，pageSize设置为100000是尽可能往大的数去设置
        List<BaseReply> nums = replyMapper.getMessageReply(message_id,0,100000);
        //然后获取每页需要显示的条数
        List<BaseReply> baseReplies = replyMapper.getMessageReply(message_id,pageNum,pageSize);
        if(baseReplies.size() != 0){
            pageDataResult.setList(baseReplies);
            pageDataResult.setTotals(nums.size());
        }
        return pageDataResult;
    }

    @Override
    public Map<String, Object> addReply(BaseReply reply) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = replyMapper.addReply(reply);
            if(!result){
                data.put("code",0);
                data.put("msg","回复失败，请联系管理！");
                logger.error("新增留言回复，结果=新增失败，数据插入异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","回复成功！");
            logger.info("新增留言回复，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增留言回复异常！", e);
            return data;
        }
        return data;
    }

}
