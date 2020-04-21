package com.tangtang.manager.service;

import com.tangtang.manager.pojo.BaseMessage;
import com.tangtang.manager.response.PageDataResult;

import java.util.Map;

public interface MessageService {

    PageDataResult getMessageByUserName(String message_owner, Integer pageNum, Integer pageSize);

    PageDataResult getMessageToMeByUserName(String message_sender, Integer pageNum, Integer pageSize);

    Map<String,Object> addMessage(BaseMessage message);

}
