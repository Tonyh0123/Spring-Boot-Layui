package com.tangtang.manager.controller.system;

import com.tangtang.manager.pojo.BaseIndexBanner;
import com.tangtang.manager.pojo.BasePolicy;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.IndexBannerService;
import com.tangtang.manager.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: IndexBannerController
 * @Description: 首页Banner管理
 * @author: tangtang
 * @version: 1.0
 * @date: 2020/03/25 15：57
 */
@Controller
@RequestMapping("banner")
public class IndexBannerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IndexBannerService indexBannerService;


    /**
     *
     * 功能描述: 跳到首页Banner列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/25 15：58
     */
    @RequestMapping("/bannerManage")
    public String bannerManage() {
        return "/banner/bannerManage";
    }


    /**
     *
     * 功能描述: 分页查询首页Banner管理列表
     *
     * @param:
     * @return:
     * @auther: tangtang
     * @date: 2020/03/25 15：59
     */
    @RequestMapping(value = "/getBannerList", method = RequestMethod.POST)
    @ResponseBody
    public PageDataResult getBannerList(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize, BaseIndexBanner indexBanner) {

        PageDataResult pdr = new PageDataResult();
        try {
            if(null == pageNum) {
                pageNum = 1;
            }
            if(null == pageSize) {
                pageSize = 10;
            }
            // 获取首页Banner列表
            pdr = indexBannerService.getBannerList(indexBanner, pageNum ,pageSize);
            logger.info("首页Banner列表查询=pdr:" + pdr);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("首页Banner列表查询异常！", e);
        }
        return pdr;
    }

    /**
     * 新增首页Banner
     * @param indexBanner
     * @return
     */
    @RequestMapping(value = "/addBanner", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addBanner(BaseIndexBanner indexBanner) {
        logger.info("新增首页Banner ------> indexBanner:" + indexBanner);
        Map<String,Object> data = new HashMap();
        data = indexBannerService.addBanner(indexBanner);
        return data;
    }

    @GetMapping("bannerShowData")
    public @ResponseBody List<BaseIndexBanner> getBannerShowData(){
        logger.info("获取首页Banner展示数据");
        List<BaseIndexBanner> indexBanners = indexBannerService.getBannerList();
        return indexBanners;
    }

    /**
     * 删除首页Banner
     * @Author tangtang
     * @param id
     * @return
     */
    @PostMapping("del")
    @ResponseBody
    public Map<String, Object> del(@RequestParam("id") Long id) {
        logger.info("删除首页Banner！id:" + id);
        Map<String, Object> data = new HashMap<>();
        data = indexBannerService.del(id);
        return data;
    }


}
