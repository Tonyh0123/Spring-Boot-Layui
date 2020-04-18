package com.tangtang.manager.controller.system;

import com.tangtang.manager.common.utils.DateUtils;
import com.tangtang.manager.pojo.BaseMessage;
import com.tangtang.manager.pojo.BaseReply;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.MessageService;
import com.tangtang.manager.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: ReplyController
 * @Description: 项目留言回复管理
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/04/18 11：57
 */
@Controller
@RequestMapping("reply")
public class ReplyController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ReplyService replyService;

    /**
     *
     * 功能描述: 分页留言回复列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/18 11：59
     */
    @RequestMapping(value = "/getReplyList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getReplyList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize,@RequestParam("message_id")String message_id) {
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            pdr = replyService.getMessageReply(message_id,pageNum ,pageSize);
            logger.info("留言回复列表查询");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("留言回复列表查询异常！", e);
        }
        return pdr;
    }

    /**
     * 新增留言回复
     * @param reply
     * @return
     */
    @RequestMapping(value = "/addReply", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addReply(BaseReply reply) {
        logger.info("新增留言回复");
        Map<String,Object> data = new HashMap();
        reply.setReply_date_time(DateUtils.getCurrentDate());
        data = replyService.addReply(reply);
        return data;
    }

}
