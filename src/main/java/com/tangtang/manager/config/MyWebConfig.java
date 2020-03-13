package com.tangtang.manager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author： tangtang
 * @Date： 2020/03/03 13:43
 * 配置虚拟路径以访问本地文件
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("file:F:/caseImages/");
        registry.addResourceHandler("/noticeFile/**").addResourceLocations("file:F:/noticeFile/");
        registry.addResourceHandler("/identifyConfirmFile/**").addResourceLocations("file:F:/identifyConfirmFile/");


    }
}
