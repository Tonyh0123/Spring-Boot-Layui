package com.tangtang.manager.controller.system;

import com.tangtang.manager.common.utils.DateUtils;
import com.tangtang.manager.dto.NoticeSerachDTO;
import com.tangtang.manager.pojo.BaseMessage;
import com.tangtang.manager.pojo.BaseNotice;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.MessageService;
import com.tangtang.manager.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: MessageController
 * @Description: 项目留言管理
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/04/18 11：57
 */
@Controller
@RequestMapping("message")
public class MessageController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageService messageService;


    /**
     *
     * 功能描述: 跳到留言列表列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/18 11：59
     */
    @RequestMapping("/messageList")
    public String toMessageList() {
        return "/message/messageList";
    }


    /**
     *
     * 功能描述: 分页查询我发出的留言列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/18 11：59
     */
    @RequestMapping(value = "/getMessageList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getMessageList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize, @RequestParam("userName")String userName) {
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            pdr = messageService.getMessageByUserName(userName, pageNum ,pageSize);
            logger.info("我发出的留言列表查询");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("我发出的留言列表查询异常！", e);
        }
        return pdr;
    }

    /**
     *
     * 功能描述: 分页查询我收到的留言列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/21 11：59
     */
    @RequestMapping(value = "/getMessageToMeList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getMessageToMeList(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize, @RequestParam("userName")String userName) {
        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            pdr = messageService.getMessageToMeByUserName(userName, pageNum ,pageSize);
            logger.info("我收到的留言列表查询");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("我收到的留言列表查询异常！", e);
        }
        return pdr;
    }

    /**
     * 新增留言
     * @param baseMessage
     * @return
     */
    @RequestMapping(value = "/addMessage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addMessage(BaseMessage baseMessage) {
        logger.info("新增留言");
        Map<String,Object> data = new HashMap();
        baseMessage.setMessage_date_time(DateUtils.getCurrentDate());
        data = messageService.addMessage(baseMessage);
        return data;
    }

}
