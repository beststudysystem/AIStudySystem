package com.AIStudySystem.content.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description 修改课程dto
 * @author Jasper
 * @date 2023/12/26 15:46
 * @version 1.1
 */
@Data
@ApiModel(value="EditCourseDto", description="修改课程信息")
public class EditCourseDto extends AddCourseDto{

    @ApiModelProperty(value = "课程id", required = true)
    private Long id;
}
