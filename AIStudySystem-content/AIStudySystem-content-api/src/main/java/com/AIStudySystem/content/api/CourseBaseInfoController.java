package com.AIStudySystem.content.api;

import com.AIStudySystem.base.model.PageParams;
import com.AIStudySystem.base.model.PageResult;
import com.AIStudySystem.content.model.dto.QueryCourseParamsDto;
import com.AIStudySystem.content.model.po.CourseBase;
import com.AIStudySystem.content.service.CourseBaseInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jasper
 * @version 1.0
 * @description 课程信息编辑接口
 * @date 2023/8/22 1:28
 */
@Api(value = "Course information management interface", tags = "Course information management interface")
@RestController  // @RestController  作用是响应json数据，相当于 @controller 和 @responseBody
public class CourseBaseInfoController {
    // 这里是一个 post 请求
    // queryCourseParams接收的是一个json对象，所以要加 @RequestBody 注解
    // 响应结果是 PageResult<CourseBase>，PageResult有数据列表+分页数据，数据列表是泛型的，这里是CourseBase类型
    // @RequestBody(required = false)：说明QueryCourseParamsDto参数不是必须的，也就是说可以不给 json 参数
    @Autowired
    CourseBaseInfoService courseBaseInfoService;

    @ApiOperation("Course query interface")
    @PostMapping ("/course/list")  // @RequestMapping支持 post get mapping
    public PageResult<CourseBase> list(PageParams pageParams, @RequestBody(required = false) QueryCourseParamsDto queryCourseParams){

        PageResult<CourseBase> courseBasePageResult = courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParams);
        return courseBasePageResult;


    }

}
