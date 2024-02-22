package com.AIStudySystem.content.service;

import com.AIStudySystem.content.model.dto.CoursePreviewDto;

/**
 * @description 课程预览、发布接口
 * @author Jasper
 * @date 2024/2/8 13:46
 * @version 1.1
 */
public interface CoursePublishService {


    /**
     * @description 获取课程预览信息
     * @param courseId 课程id
     * @return com.AIStudySystem.content.model.dto.CoursePreviewDto
     * @author Jasper
     * @date 2024/2/8 13:46
     */
    public CoursePreviewDto getCoursePreviewInfo(Long courseId);

    /**
     * @description 提交审核
     * @param courseId  课程id
     * @return void
     * @author Jasper
     * @date 2024/2/15 16:41
     */
    public void commitAudit(Long companyId,Long courseId);

    /**
     * @description 课程发布接口
     * @param companyId 机构id
     * @param courseId 课程id
     * @return void
     * @author Jasper
     * @date 2024/2/21 14:40
     */
    public void publish(Long companyId,Long courseId);




}

