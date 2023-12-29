package com.AIStudySystem.content.service;

import com.AIStudySystem.base.model.PageParams;
import com.AIStudySystem.base.model.PageResult;
import com.AIStudySystem.content.model.dto.AddCourseDto;
import com.AIStudySystem.content.model.dto.CourseBaseInfoDto;
import com.AIStudySystem.content.model.dto.EditCourseDto;
import com.AIStudySystem.content.model.dto.QueryCourseParamsDto;
import com.AIStudySystem.content.model.po.CourseBase;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @description 课程基本信息管理业务接口
 * @author Jasper
 * @date 2023/9/3 11:23
 * @version 1.0
 */
public interface CourseBaseInfoService {
    // 课程分页查询 (参考 content.Api 里的 CourseBaseInfoController 接收 和 返回 的参数，因为是 controller 调这个 service)
    public PageResult<CourseBase> queryCourseBaseList(PageParams pageParams, QueryCourseParamsDto queryCourseParams);

    /**
     * This method is used to create a new course base.
     *
     * @param companyID The ID of the company that owns the course.(机构id)
     * @param dto The data transfer object containing the details of the course to be added.（课程信息）
     * @return CourseBaseInfoDto Returns the details of the newly created course base.（课程详细信息）
     */
    public CourseBaseInfoDto createCourseBase(Long companyID, AddCourseDto dto);

    /**
     * @description 根据id查询课程基本信息
     * @param courseId  课程id
     * @return com.AIStudySystem.content.model.dto.CourseBaseInfoDto
     * @author Jasper
     * @date 2023/12/26 15:59
     */
    public CourseBaseInfoDto getCourseBaseInfo(Long courseId);


    /**
     * @description 修改课程信息
     * @param companyId  机构id
     * @param dto  课程信息
     * @return com.AIStudySystem.content.model.dto.CourseBaseInfoDto
     * @author Jasper
     * @date 2023/12/26 16:56
     */
    public CourseBaseInfoDto updateCourseBase(Long companyId, EditCourseDto dto);


}
