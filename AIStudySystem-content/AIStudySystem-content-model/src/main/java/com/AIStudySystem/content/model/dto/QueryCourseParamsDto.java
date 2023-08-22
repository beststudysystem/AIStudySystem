package com.AIStudySystem.content.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author Jasper
 * @version 1.0
 * @description 课程查询条件模型类（接收的参数）
 * @date 2023/8/22 1:04
 */
// 建在 AIStudySystem-content 的 content.model 下是因为这部分是课程查询特有的参数
// 都需要的参数（分页信息）配在 AIStudySystem-base 的 base.model 里了
@Data
@ToString
public class QueryCourseParamsDto {
    //审核状态
    private String auditStatus;
    //课程名称
    private String courseName;
    //发布状态
    private String publishStatus;

}
