package com.tangtang.manager.dao;

import com.tangtang.manager.pojo.BaseStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseCheckStatusMapper {
    List<BaseStudent> getStudentStatus(@Param("userId") String userId);

    boolean updateBaseStudent(BaseStudent student);
}
