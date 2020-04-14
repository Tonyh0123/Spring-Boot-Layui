package com.tangtang;

import com.tangtang.manager.common.utils.DateUtils;
import com.tangtang.manager.dao.BaseProjectDeclarationMapper;
import com.tangtang.manager.service.ProjectDeclarationService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DataTest {
    static ProjectDeclarationService projectDeclarationService;

    public ProjectDeclarationService getProjectDeclarationService() {
        return projectDeclarationService;
    }

    public void setProjectDeclarationService(ProjectDeclarationService projectDeclarationService) {
        this.projectDeclarationService = projectDeclarationService;
    }

    public static void main(String[] args) throws Throwable{
        String schoolName = projectDeclarationService.getSchoolNameByUserId("42");
        System.out.println(schoolName);
    }
}
