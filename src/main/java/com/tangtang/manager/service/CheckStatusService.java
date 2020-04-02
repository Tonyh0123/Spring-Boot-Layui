package com.tangtang.manager.service;

import com.tangtang.manager.pojo.BaseStudent;

import java.util.List;
import java.util.Map;


public interface CheckStatusService {
    List<BaseStudent> getStudentStatus(String userId);

    Map<String,Object> updateBaseStudent(BaseStudent student);

}
