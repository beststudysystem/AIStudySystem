package com.AIStudySystem.content.api;

import com.AIStudySystem.content.model.dto.CourseCategoryTreeDto;
import com.AIStudySystem.content.model.po.CourseCategory;
import com.AIStudySystem.content.service.CourseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jasper
 * @version 1.0
 * @description 课程分类接口
 * @date 2023/9/7 15:42
 */
@Slf4j
@RestController
public class CourseCategoryController {

    @Autowired
    CourseCategoryService courseCategoryService;

    @GetMapping("/course-category/tree-nodes")
    public List<CourseCategoryTreeDto> queryTreeNodes() {
        return courseCategoryService.queryTreeNodes("1");
    }
}

