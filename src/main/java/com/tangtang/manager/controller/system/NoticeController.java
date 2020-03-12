package com.tangtang.manager.controller.system;

import com.tangtang.manager.dto.SchoolRegistrationDTO;
import com.tangtang.manager.dto.StudentRegistrationDTO;
import com.tangtang.manager.pojo.BaseAdminUser;
import com.tangtang.manager.pojo.BaseNotice;
import com.tangtang.manager.pojo.BaseSuccessfulCase;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.AdminUserService;
import com.tangtang.manager.service.CaseService;
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
 * @Title: NoticeController
 * @Description: 通知公告管理
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/03/09 11：57
 */
@Controller
@RequestMapping("notice")
public class NoticeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NoticeService noticeService;


    /**
     *
     * 功能描述: 跳到通知公告列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/09 11：58
     */
    @RequestMapping("/noticeManage")
    public String userManage() {
        return "/notice/noticeManage";
    }


    /**
     *
     * 功能描述: 分页查询通知公告管理列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/09 11：59
     */
    @RequestMapping(value = "/getNoticeList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getUserList(@RequestParam("pageNum") Integer pageNum,
                                      @RequestParam("pageSize") Integer pageSize,BaseNotice baseNotice) {

        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取公告列表
            pdr = noticeService.getNoticeList(baseNotice, pageNum ,pageSize);
            logger.info("公告列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("公告列表查询异常！", e);
        }
        return pdr;
    }

    /**
     * 新增通知公告
     * @param baseNotice
     * @return
     */
    @RequestMapping(value = "/addNotice", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addNotice(BaseNotice baseNotice) {
        logger.info("新增公告 ------> baseNotice:" + baseNotice);
        Map<String,Object> data = new HashMap();
        data = noticeService.addNotice(baseNotice);
        return data;
    }

    @GetMapping("noticeShowData")
    public @ResponseBody List<BaseNotice> getNoticeShowData(){
        logger.info("获取案例展示数据");
        List<BaseNotice> baseNotices = noticeService.getNoticeList();
        return baseNotices;
    }

    /**
     * 删除通知公告
     * @Author tangtang
     * @param id
     * @return
     */
    @PostMapping("del")
    @ResponseBody
    public Map<String, Object> del(@RequestParam("id") Long id) {
        logger.info("删除通知公告！id:" + id);
        Map<String, Object> data = new HashMap<>();
        data = noticeService.del(id);
        return data;
    }


}
