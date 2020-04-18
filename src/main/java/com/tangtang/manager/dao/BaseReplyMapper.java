package com.tangtang.manager.dao;

import com.tangtang.manager.pojo.BaseMessage;
import com.tangtang.manager.pojo.BaseReply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mapper.MyMapper;

import java.util.List;

@Repository
public interface BaseReplyMapper extends MyMapper<BaseMessage> {

    boolean addReply(BaseReply reply);

    List<BaseReply> getMessageReply(@Param("message_id")String message_id, Integer pageNum, Integer pageSize);

}
