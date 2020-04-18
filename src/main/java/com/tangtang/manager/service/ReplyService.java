package com.tangtang.manager.service;

import com.tangtang.manager.pojo.BaseMessage;
import com.tangtang.manager.pojo.BaseReply;
import com.tangtang.manager.response.PageDataResult;

import java.util.Map;

public interface ReplyService {

    PageDataResult getMessageReply(String message_id, Integer pageNum, Integer pageSize);

    Map<String,Object> addReply(BaseReply reply);

}
