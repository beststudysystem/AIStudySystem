package com.AIStudySystem.content.service;

import com.AIStudySystem.content.model.dto.SaveTeachplanDto;
import com.AIStudySystem.content.model.dto.TeachplanDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @description 课程基本信息管理业务接口
 * @author Jasper
 * @date 2023/12/30 00:46
 * @version 1.1
 */
public interface TeachplanService {

    /**
     * @description 查询课程计划树型结构
     * @param courseId  课程id
     * @return List<TeachplanDto>
     * @author Jasper
     * @date 2023/12/30 00:47
     */
    public List<TeachplanDto> findTeachplanTree(long courseId);

    /**
     * @description 只在课程计划
     * @param teachplanDto  课程计划信息
     * @return void
     * @author Jasper
     * @date 2023/12/30 01:43
     */
    public void saveTeachplan(SaveTeachplanDto teachplanDto);



}
