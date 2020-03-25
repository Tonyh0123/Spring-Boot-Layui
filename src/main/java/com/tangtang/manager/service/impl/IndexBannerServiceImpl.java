package com.tangtang.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tangtang.manager.dao.BaseIndexBannerMapper;
import com.tangtang.manager.dao.BasePolicyMapper;
import com.tangtang.manager.pojo.BaseIndexBanner;
import com.tangtang.manager.pojo.BasePolicy;
import com.tangtang.manager.response.PageDataResult;
import com.tangtang.manager.service.IndexBannerService;
import com.tangtang.manager.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexBannerServiceImpl implements IndexBannerService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseIndexBannerMapper indexBannerMapper;


    @Override
    public PageDataResult getBannerList(BaseIndexBanner baseIndexBanner, Integer pageNum, Integer pageSize) {
        PageDataResult pageDataResult = new PageDataResult();
        List<BaseIndexBanner> indexBanners = indexBannerMapper.getBannerList(baseIndexBanner);
        PageHelper.startPage(pageNum, pageSize);
        if(indexBanners.size() != 0){
            PageInfo<BaseIndexBanner> pageInfo = new PageInfo<>(indexBanners);
            pageDataResult.setList(indexBanners);
            pageDataResult.setTotals((int) pageInfo.getTotal());
        }
        return pageDataResult;
    }

    @Override
    public Map<String, Object> addBanner(BaseIndexBanner indexBanner) {
        Map<String,Object> data = new HashMap();
        try {
            boolean result = indexBannerMapper.addBanner(indexBanner);
            if(!result){
                data.put("code",0);
                data.put("msg","操作失败，请检查信息输入是否正确！");
                logger.error("新增首页Banner，结果=新增失败，数据插入异常！");
                return data;
            }
            data.put("code",1);
            data.put("msg","新增成功！");
            logger.info("新增首页Banner，结果=新增成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增首页Banner异常！", e);
            return data;
        }
        return data;
    }

    @Override
    public List<BaseIndexBanner> getBannerList() {
        return indexBannerMapper.getBannerList();
    }

    @Override
    public Map<String, Object> del(long id) {
        Map<String, Object> data = new HashMap<>();
        try {
            int result = indexBannerMapper.deleteByPrimaryKey(id);
            if(result == 0){
                data.put("code",0);
                data.put("msg","删除失败，id:" + id);
                logger.error("删除失败");
                return data;
            }
            data.put("code",1);
            data.put("msg","首页Banner删除成功，id:" + id);
            logger.info("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("删除首页Banner异常！", e);
        }
        return data;
    }

}
