package com.AIStudySystem.content.service;

import com.AIStudySystem.content.model.dto.BindTeachplanMediaDto;
import com.AIStudySystem.content.model.dto.SaveTeachplanDto;
import com.AIStudySystem.content.model.dto.TeachplanDto;
import com.AIStudySystem.content.model.po.TeachplanMedia;
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

    /**
     * @description 教学计划绑定媒资
     * @param bindTeachplanMediaDto
     * @return com.AIStudySystem.content.model.po.TeachplanMedia
     * @author Jasper
     * @date 2024/2/7 15:23
     */
    public TeachplanMedia associationMedia(BindTeachplanMediaDto bindTeachplanMediaDto);

    /** 解绑教学计划与媒资信息
     * @param teachPlanId       教学计划id
     * @param mediaId           媒资信息id
     */
    void unassociationMedia(Long teachPlanId, String mediaId);




}
