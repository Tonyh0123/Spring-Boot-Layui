package com.tangtang.manager.dao;

import com.tangtang.manager.dto.NoticeSerachDTO;
import com.tangtang.manager.pojo.BaseMessage;
import com.tangtang.manager.pojo.BaseNotice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface BaseMessageMapper extends MyMapper<BaseMessage> {

    boolean addMessage(BaseMessage message);

    List<BaseMessage> getMessageByUserName(@Param("message_owner") String message_owner, Integer pageNum, Integer pageSize);

    List<BaseMessage> getMessageToMeByUserName(@Param("message_sender") String message_sender, Integer pageNum, Integer pageSize);


}
