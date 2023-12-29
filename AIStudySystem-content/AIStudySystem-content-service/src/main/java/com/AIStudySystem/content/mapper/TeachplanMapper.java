package com.AIStudySystem.content.mapper;

import com.AIStudySystem.content.model.dto.TeachplanDto;
import com.AIStudySystem.content.model.po.Teachplan;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程计划 Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface TeachplanMapper extends BaseMapper<Teachplan> {
    /**
     * @description 查询某课程的课程计划，组成树型结构
     * @param courseId
     * @return com.AIStudySystem.content.model.dto.TeachplanDto
     * @author Jasper
     * @date 2023/12/28 23:36
     * @version 1.1
     */
    public List<TeachplanDto> selectTreeNodes(long courseId);

}
