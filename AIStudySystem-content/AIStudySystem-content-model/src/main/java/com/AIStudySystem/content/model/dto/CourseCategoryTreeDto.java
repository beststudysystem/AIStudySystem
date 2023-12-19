package com.AIStudySystem.content.model.dto;

import com.AIStudySystem.content.model.po.CourseCategory;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Jasper
 * @version 1.0
 * @description 课程分类树型结点dto
 * @date 2023/9/7 15:30
 */
@Data
public class CourseCategoryTreeDto extends CourseCategory implements Serializable {

    List<CourseCategoryTreeDto> childrenTreeNodes;

}
