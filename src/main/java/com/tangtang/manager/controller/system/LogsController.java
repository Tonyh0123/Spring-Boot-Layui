package com.tangtang.manager.controller.system;

import com.tangtang.manager.common.utils.DateUtils;
import com.tangtang.manager.common.utils.LogsUtils;
import com.tangtang.manager.dto.NoticeSerachDTO;
import com.tangtang.manager.pojo.BaseNotice;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: NoticeController
 * @Description: 日志管理
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/04/19 11：57
 */
@Controller
@RequestMapping("logs")
public class LogsController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     *
     * 功能描述: 跳到日志显示界面
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/04/19 11：58
     */
    @RequestMapping("/logsPage")
    public String userManage() {
        return "/logsPage";
    }

    /**
     * 查看系统运行日志
     * @Author tangtang
     * @return
     */
    @PostMapping("checkLogs")
    @ResponseBody
    public Map<String, Object> checkLogs() {
        LogsUtils logsUtils = new LogsUtils();
        logger.info("查看系统运行日志！");
        Map<String, Object> data = new HashMap<>();
        ArrayList<String> listFileName = new ArrayList<String>();
        List<String> nearly30days = DateUtils.getNearly30days();
        String nowadays = DateUtils.getNowDateString();

        logsUtils.getAllFileName("C:\\Users\\Adminstrador\\logs\\",listFileName);
        for(String name:listFileName){
            if(name.endsWith(".gz")){
                logsUtils.doUncompressFile(name); //解压缩.gz文件
            }
        }

        for(int i=0; i<nearly30days.size(); i++){
            for(String name:listFileName){
                if(name.contains(nearly30days.get(i)) && !name.endsWith(".gz")){
                    List<String> list = new ArrayList<>();
                    logsUtils.readFile(name,list);
                    data.put(nearly30days.get(i),list);
                }else if(name.endsWith(".log") && nearly30days.get(i).equals(nowadays)){
                    List<String> list = new ArrayList<>();
                    logsUtils.readFile(name,list);
                    data.put(nearly30days.get(i),list);
                }
            }
        }
//        logsUtils.getAllFileName("C:\\Users\\Adminstrador\\logs\\",listFileName);
//        for(String name:listFileName){
//            if(name.endsWith(".0")){
////                doUncompressFile(name); 解压缩.gz文件
//                logsUtils.readFile(name,list);
//            }else if (name.endsWith(".log")){
//                logsUtils.readFile(name,list);
//            }
//        }
        data.put("nearly30days",nearly30days);

        return data;
    }


}
