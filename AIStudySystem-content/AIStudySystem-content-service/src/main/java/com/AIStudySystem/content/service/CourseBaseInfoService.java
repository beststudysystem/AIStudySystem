package com.AIStudySystem.content.service;

import com.AIStudySystem.base.model.PageParams;
import com.AIStudySystem.base.model.PageResult;
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
}
