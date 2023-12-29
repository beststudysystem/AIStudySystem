package com.AIStudySystem.content.model.dto;

import com.AIStudySystem.content.model.po.Teachplan;
import com.AIStudySystem.content.model.po.TeachplanMedia;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @description 课程计划树型结构dto
 * @author Jasper
 * @date 2023/12/28 16:12
 * @version 1.1
 */
@Data
@ToString
public class TeachplanDto extends Teachplan {

    //课程计划关联的媒资信息
    TeachplanMedia teachplanMedia;

    //子结点
    List<TeachplanDto> teachPlanTreeNodes;

}
